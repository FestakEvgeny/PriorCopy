package fetskovich.evgeny.networking.api.exchange

import fetskovich.evgeny.networking.ktor.get
import fetskovich.evgeny.networking.ktor.requests.HttpRequestExecutor

interface ExchangeRateApi {

    suspend fun getExchangeRates(): Any // TODO
}

internal class ExchangeRateApiImpl(
    private val requestExecutor: HttpRequestExecutor
) : ExchangeRateApi {

    override suspend fun getExchangeRates(): Any {
        return requestExecutor.get(
            url = TODO()
        )
    }
}