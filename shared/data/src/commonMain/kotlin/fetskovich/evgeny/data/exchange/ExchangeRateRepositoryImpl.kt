package fetskovich.evgeny.data.exchange

import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import fetskovich.evgeny.data.database.ExchangeRateModel
import fetskovich.evgeny.data.database.dao.ExchangeRateDao
import fetskovich.evgeny.domain.exchange.ExchangeRateRepository
import fetskovich.evgeny.entity.currency.Currency
import fetskovich.evgeny.entity.exchange.ExchangeRate
import fetskovich.evgeny.networking.api.exchange.ExchangeRateApi
import fetskovich.evgeny.networking.api.exchange.model.ExchangeRateApiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExchangeRateRepositoryImpl(
    private val api: ExchangeRateApi,
    private val exchangeRateDao: ExchangeRateDao,
    private val coroutinesContextProvider: CoroutinesContextProvider,
) : ExchangeRateRepository {

    private val scope = CoroutineScope(coroutinesContextProvider.io + SupervisorJob())

    private val exchangeRate: MutableStateFlow<ExchangeRate?> = MutableStateFlow(null)

    override fun getExchangeRate(currency: Currency): Flow<ExchangeRate?> {
        if (exchangeRate.value == null) {
            scope.launch {

                val latestDbModel = exchangeRateDao.getActualExchangeRate()
                if (latestDbModel == null) {
                    retrieveAndUpdateExchangeRate(currency = currency)
                } else {
                    exchangeRate.update { latestDbModel.toEntity() }
                }
            }
        }
        return exchangeRate
    }

    private suspend fun retrieveAndUpdateExchangeRate(currency: Currency) {
        val result = api.getExchangeRates(currency)
        result.fold(
            onSuccess = { apiModel ->
                // insert to database
                exchangeRateDao.createExchangeRate(
                    model = apiModel.toExchangeRateModel()
                )

                // retrieve from database the actual one
                val actualExchangeRate = exchangeRateDao.getActualExchangeRate()?.toEntity()

                exchangeRate.update { actualExchangeRate }
            },
            onFailure = {
                // do nothing
            }
        )
    }
}

private fun ExchangeRateApiModel.toExchangeRateModel(): ExchangeRateModel {
    return ExchangeRateModel(
        id = 0L,
        nextUpdateTime = 0, // TODO
        lastUpdateTime = 0, // TODO
        baseCode = this.baseCode.name,
        conversionRateUsd = this.conversionRatesModel.usd,
        conversionRateByn = this.conversionRatesModel.byn,
        conversionRateEur = this.conversionRatesModel.eur,
        conversionRatePln = this.conversionRatesModel.pln,
        conversionRateRub = this.conversionRatesModel.rub,
    )
}

private fun ExchangeRateModel.toEntity(): ExchangeRate {
    return ExchangeRate(
        id = this.id,
        nextUpdate = this.nextUpdateTime,
        baseCurrency = Currency.fromString(this.baseCode),
        conversionRates = mapOf(
            Currency.USD to this.conversionRateUsd,
            Currency.BYN to this.conversionRateByn,
            Currency.EUR to this.conversionRateEur,
            Currency.PLN to this.conversionRatePln,
            Currency.RUB to this.conversionRateRub
        )
    )
}