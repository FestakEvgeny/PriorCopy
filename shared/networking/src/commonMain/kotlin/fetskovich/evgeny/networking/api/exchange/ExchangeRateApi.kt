package fetskovich.evgeny.networking.api.exchange

import fetskovich.evgeny.entity.currency.Currency
import fetskovich.evgeny.networking.api.exchange.model.ExchangeRateModel
import fetskovich.evgeny.networking.ktor.get
import fetskovich.evgeny.networking.ktor.requests.HttpRequestExecutor

interface ExchangeRateApi {

    suspend fun getExchangeRates(currency: Currency): Result<ExchangeRateModel>
}

internal class ExchangeRateApiImpl(
    private val requestExecutor: HttpRequestExecutor
) : ExchangeRateApi {

    override suspend fun getExchangeRates(currency: Currency): Result<ExchangeRateModel> {
        return try {
            val data: ExchangeRateModel = requestExecutor.get(
                url = "/latest/${currency.currencyValue}",
            )
            Result.success(data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}