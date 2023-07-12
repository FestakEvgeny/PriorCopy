package fetskovich.evgeny.networking.api

import fetskovich.evgeny.networking.ktor.setup.logging.LogLevel
import fetskovich.evgeny.networking.ktor.setup.logging.Logger
import fetskovich.evgeny.networking.ktor.setup.logging.LoggerConfig
import fetskovich.evgeny.networking.ktor.setup.timeout.KtorTimeout

class KtorApiSetupConfig(
    val bearer: String? = null,
    val baseUrl: String,
    val headersMap: Map<String, String> = emptyMap(),
    val queryParamsMap: Map<String, String> = emptyMap(),
    val loggerConfig: LoggerConfig = LoggerConfig(
        logLevel = LogLevel.ALL,
        logger = Logger.SIMPLE,
    ),
    val timeout: KtorTimeout = KtorTimeout(),
)