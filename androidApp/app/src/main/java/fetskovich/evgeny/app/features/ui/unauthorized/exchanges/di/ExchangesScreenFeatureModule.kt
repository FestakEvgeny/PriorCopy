package fetskovich.evgeny.app.features.ui.unauthorized.exchanges.di

import fetskovich.evgeny.app.core.currency.CoreCurrencyFormatter
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.ExchangesViewModel
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mapper.ExchangesRatesMapper
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesMviHandler
import fetskovich.evgeny.app.features.viewmodel.ViewModelProviderFactory
import fetskovich.evgeny.domain.usecase.exchange.di.exchangeRateDiModule
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance

val exchangesScreenFeatureModule = DI.Module("Exchanges") {
    import(exchangeRateDiModule)

    bind<ExchangesMviHandler>() with factory {
        ExchangesMviHandler(
            resourceProvider = instance()
        )
    }

    bind<ExchangesRatesMapper>() with factory {
        ExchangesRatesMapper(
            currencyFormatter = instance(CoreCurrencyFormatter.DI_TAG),
        )
    }

    bind<ViewModelProviderFactory<ExchangesViewModel>>() with factory {
        ViewModelProviderFactory {
            ExchangesViewModel(
                mviHandler = instance(),
                exchangesRatesMapper = instance(),
                observeExchangeRateUseCase = instance(),
                coroutinesContextProvider = instance(),
            )
        }
    }
}