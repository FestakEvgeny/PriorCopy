package fetskovich.evgeny.networking.api.exchange

import fetskovich.evgeny.entity.currency.Currency
import fetskovich.evgeny.networking.api.exchange.model.ExchangeRateApiModel
import fetskovich.evgeny.networking.ktor.get
import fetskovich.evgeny.networking.ktor.requests.HttpRequestExecutor

interface ExchangeRateApi {

    suspend fun getExchangeRates(currency: Currency): Result<ExchangeRateApiModel>
}

internal class ExchangeRateApiImpl(
    private val requestExecutor: HttpRequestExecutor
) : ExchangeRateApi {

    override suspend fun getExchangeRates(currency: Currency): Result<ExchangeRateApiModel> {
        return try {
            val data: ExchangeRateApiModel = requestExecutor.get(
                url = "latest/${currency.currencyValue}",
            )
            Result.success(data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}