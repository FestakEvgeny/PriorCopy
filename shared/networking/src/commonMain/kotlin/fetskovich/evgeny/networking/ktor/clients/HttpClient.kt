package fetskovich.evgeny.networking.ktor.clients

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.util.appendIfNameAbsent
import fetskovich.evgeny.networking.ktor.setup.timeout.KtorTimeout
import fetskovich.evgeny.networking.ktor.setup.json.KtorJson
import fetskovich.evgeny.networking.ktor.setup.logging.LogLevel
import fetskovich.evgeny.networking.ktor.setup.logging.Logger
import fetskovich.evgeny.networking.ktor.setup.logging.LoggerConfig
import fetskovich.evgeny.networking.ktor.setup.logging.toKtor
import kotlin.time.DurationUnit

internal fun createHttpClient(
    bearer: String?,
    baseUrl: String,
    headersMap: Map<String, String> = emptyMap(),
    queryParamsMap: Map<String, String> = emptyMap(),
    loggerConfig: LoggerConfig = LoggerConfig(
        logLevel = LogLevel.ALL,
        logger = Logger.DEFAULT,
    ),
    timeout: KtorTimeout = KtorTimeout(),
): HttpClient = HttpClient {

    bearer?.let { bearer ->
        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(accessToken = bearer, refreshToken = "")
                }
            }
        }
    }

    install(ContentNegotiation) {
        register(ContentType.Application.Json, KotlinxSerializationConverter(KtorJson))
    }

    install(HttpTimeout) {
        timeout.request?.let { requestTimeout ->
            requestTimeoutMillis = requestTimeout.toLong(DurationUnit.MILLISECONDS)
        }
        timeout.connect?.let { connectTimeout ->
            connectTimeoutMillis = connectTimeout.toLong(DurationUnit.MILLISECONDS)
        }
        timeout.socket?.let { socketTimeout ->
            socketTimeoutMillis = socketTimeout.toLong(DurationUnit.MILLISECONDS)
        }
    }

    defaultRequest {
        url(baseUrl)
        queryParamsMap.onEach { (key, value) -> url.parameters.appendIfNameAbsent(key, value) }
        headersMap.onEach { (key, value) -> headers.appendIfNameAbsent(key, value) }
    }

    install(Logging) {
        logger = loggerConfig.logger.toKtor()
        level = loggerConfig.logLevel.toKtor()
    }

    expectSuccess = true
}