package fetskovich.evgeny.data.exchange

import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import fetskovich.evgeny.domain.exchange.ExchangeRateRepository
import fetskovich.evgeny.entity.currency.Currency
import fetskovich.evgeny.entity.exchange.ExchangeRate
import fetskovich.evgeny.networking.api.exchange.ExchangeRateApi
import fetskovich.evgeny.networking.api.exchange.model.ExchangeRateApiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExchangeRateRepositoryImpl(
    private val api: ExchangeRateApi,
    private val coroutinesContextProvider: CoroutinesContextProvider,
) : ExchangeRateRepository {

    private val scope = CoroutineScope(coroutinesContextProvider.io + SupervisorJob())

    private val exchangeRate: MutableStateFlow<Result<ExchangeRate>?> = MutableStateFlow(null)
    val exchangeRateFlow: StateFlow<Result<ExchangeRate>?> = exchangeRate

    override fun getExchangeRate(currency: Currency): Flow<Result<ExchangeRate>?> {
        if (exchangeRate.value == null) {
            scope.launch {
                val result = api.getExchangeRates(currency)
                exchangeRate.update {
                    result.map { it.toExchangeRate() }
                }
            }
        }
        return exchangeRateFlow
    }
}

private fun ExchangeRateApiModel.toExchangeRate(): ExchangeRate {
    return ExchangeRate(
        id = 0L,
        nextUpdate = 0L,
        baseCurrency = this.baseCode,
        conversionRates = mapOf(
            Currency.USD to this.conversionRatesModel.usd,
            Currency.BYN to this.conversionRatesModel.byn,
            Currency.EUR to this.conversionRatesModel.eur,
            Currency.PLN to this.conversionRatesModel.pln,
            Currency.RUB to this.conversionRatesModel.rub
        )
    )
}