package fetskovich.evgeny.networking.api

import fetskovich.evgeny.networking.api.exchange.ExchangeRateApi
import fetskovich.evgeny.networking.api.exchange.createExchangeRateApi

interface KtorApiFactory {

    fun provideExchangeRate(config: KtorApiSetupConfig): ExchangeRateApi
}

internal class KtorApiFactoryImpl : KtorApiFactory {

    override fun provideExchangeRate(config: KtorApiSetupConfig) = createExchangeRateApi(config)
}

fun createKtorApiFactory(): KtorApiFactory = KtorApiFactoryImpl()
