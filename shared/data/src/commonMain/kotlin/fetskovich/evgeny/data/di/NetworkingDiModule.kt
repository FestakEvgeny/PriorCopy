package fetskovich.evgeny.data.di

import fetskovich.evgeny.networking.api.KtorApiFactory
import fetskovich.evgeny.networking.api.KtorApiSetupConfig
import fetskovich.evgeny.networking.api.createKtorApiFactory
import fetskovich.evgeny.networking.api.exchange.ExchangeRateApi
import fetskovich.evgeny.networking.ktor.setup.logging.LogLevel
import fetskovich.evgeny.networking.ktor.setup.logging.Logger
import fetskovich.evgeny.networking.ktor.setup.logging.LoggerConfig
import fetskovich.evgeny.networking.ktor.setup.timeout.KtorTimeout
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val networkingDiModule = DI.Module("NetworkingApi") {
    bind<KtorApiFactory>() with singleton { createKtorApiFactory() }
    bind<ExchangeRateApi>() with singleton {
        createExchangeRateApi(
            ktorApiFactory = instance(),
            apiKey = "ab3aa5d7d0715faf1114df7a" // TODO safe strorage
        )
    }
}

private fun createExchangeRateApi(
    ktorApiFactory: KtorApiFactory,
    apiKey: String,
): ExchangeRateApi {
    return ktorApiFactory.provideExchangeRate(
        config = KtorApiSetupConfig(
            baseUrl = "https://v6.exchangerate-api.com/v6/$apiKey/",
            loggerConfig = createLoggedConfig(),
            timeout = createKtorTimeout(),
        )
    )
}

private fun createKtorTimeout() = KtorTimeout()

private fun createLoggedConfig() = LoggerConfig(
    logLevel = LogLevel.ALL,
    logger = Logger.DEFAULT,
)