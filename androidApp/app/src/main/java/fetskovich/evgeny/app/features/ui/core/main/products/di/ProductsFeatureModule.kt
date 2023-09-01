package fetskovich.evgeny.app.features.ui.core.main.products.di

import fetskovich.evgeny.app.core.currency.CoreCurrencyFormatter
import fetskovich.evgeny.app.features.ui.core.main.products.ProductsScreenViewModel
import fetskovich.evgeny.app.features.ui.core.main.products.mapper.BankCardToListItemMapper
import fetskovich.evgeny.app.features.ui.core.main.products.mapper.ExchangeRateToItemMapper
import fetskovich.evgeny.app.features.ui.core.main.products.mapper.NewsToListItemMapper
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductsScreenMviHandler
import fetskovich.evgeny.app.features.viewmodel.ViewModelProviderFactory
import fetskovich.evgeny.domain.usecase.card.data.ObserveBankCardsUseCase
import fetskovich.evgeny.domain.usecase.news.di.newsDiModule
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance
import org.kodein.di.provider

val productsFeatureModule = DI.Module("Products") {
    import(newsDiModule)

    bind<ObserveBankCardsUseCase>() with factory {
        ObserveBankCardsUseCase(
            repository = instance(),
        )
    }

    bind<ProductsScreenMviHandler>() with factory {
        ProductsScreenMviHandler(
            resourceProvider = instance(),
        )
    }

    bind<BankCardToListItemMapper>() with factory {
        BankCardToListItemMapper(
            currencyFormatter = instance(CoreCurrencyFormatter.DI_TAG)
        )
    }

    bind<NewsToListItemMapper>() with provider { NewsToListItemMapper() }

    bind<ExchangeRateToItemMapper>() with factory {
        ExchangeRateToItemMapper(
            currencyFormatter = instance(CoreCurrencyFormatter.DI_TAG)
        )
    }

    bind<ViewModelProviderFactory<ProductsScreenViewModel>>() with factory {
        ViewModelProviderFactory {
            ProductsScreenViewModel(
                mviStateHandler = instance(),
                observeBankCardsUseCase = instance(),
                bankCardsMapper = instance(),
                newsToListItemMapper = instance(),
                getNewsUseCase = instance(),
                coroutinesContextProvider = instance(),
                resourceProvider = instance(),
                observeExchangeRateUseCase = instance(),
                exchangeRateToItemMapper = instance(),
            )
        }
    }
}