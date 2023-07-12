package fetskovich.evgeny.networking.api.exchange

import fetskovich.evgeny.networking.api.KtorApiSetupConfig
import fetskovich.evgeny.networking.ktor.provideKtorRequestExecutor

internal fun createExchangeRateApi(
    config: KtorApiSetupConfig,
): ExchangeRateApi {
    return ExchangeRateApiImpl(
        provideKtorRequestExecutor(
            bearer = config.bearer,
            baseUrl = config.baseUrl,
            headersMap = config.headersMap,
            queryParamsMap = config.queryParamsMap,
            loggerConfig = config.loggerConfig,
            timeout = config.timeout
        )
    )
}