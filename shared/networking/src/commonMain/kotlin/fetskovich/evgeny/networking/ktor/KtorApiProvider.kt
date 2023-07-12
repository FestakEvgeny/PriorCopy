package fetskovich.evgeny.networking.ktor

import fetskovich.evgeny.networking.ktor.clients.createHttpClient
import fetskovich.evgeny.networking.ktor.requests.HttpRequestExecutor
import fetskovich.evgeny.networking.ktor.requests.KtorRequestExecutor
import fetskovich.evgeny.networking.ktor.setup.logging.LogLevel
import fetskovich.evgeny.networking.ktor.setup.logging.Logger
import fetskovich.evgeny.networking.ktor.setup.logging.LoggerConfig
import fetskovich.evgeny.networking.ktor.setup.timeout.KtorTimeout

fun provideKtorRequestExecutor(
    bearer: String?,
    baseUrl: String,
    headersMap: Map<String, String> = emptyMap(),
    queryParamsMap: Map<String, String> = emptyMap(),
    loggerConfig: LoggerConfig = LoggerConfig(
        logLevel = LogLevel.ALL,
        logger = Logger.DEFAULT,
    ),
    timeout: KtorTimeout = KtorTimeout(),
): HttpRequestExecutor {
    return KtorRequestExecutor(
        httpClient = createHttpClient(
            bearer = bearer,
            baseUrl = baseUrl,
            headersMap = headersMap,
            queryParamsMap = queryParamsMap,
            loggerConfig = loggerConfig,
            timeout = timeout
        ),
    )
}
