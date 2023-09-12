package fetskovich.evgeny.domain.usecase.exchange.di

import fetskovich.evgeny.domain.usecase.exchange.ObserveExchangeRateUseCase
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val exchangeRateDiModule = DI.Module("ExchangeRates") {
    bind<ObserveExchangeRateUseCase>() with singleton {
        ObserveExchangeRateUseCase(
            repository = instance(),
        )
    }
}