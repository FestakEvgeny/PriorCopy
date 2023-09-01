package fetskovich.evgeny.domain.usecase.news.di

import fetskovich.evgeny.domain.usecase.exchange.ObserveExchangeRateUseCase
import fetskovich.evgeny.domain.usecase.news.GetNewsUseCase
import fetskovich.evgeny.domain.usecase.news.GetSingleNewsUseCase
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance
import org.kodein.di.singleton

val newsDiModule = DI.Module("News") {
    bind<GetNewsUseCase>() with singleton { GetNewsUseCase() }
    bind<GetSingleNewsUseCase>() with factory {
        GetSingleNewsUseCase(
            getNewsUseCase = instance(),
        )
    }
    bind<ObserveExchangeRateUseCase>() with singleton {
        ObserveExchangeRateUseCase(
            repository = instance(),
        )
    }
}