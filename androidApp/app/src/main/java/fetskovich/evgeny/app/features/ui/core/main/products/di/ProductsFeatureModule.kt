package fetskovich.evgeny.app.features.ui.core.main.products.di

import fetskovich.evgeny.app.features.ui.core.main.products.ProductsScreenViewModel
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductsScreenMviHandler
import fetskovich.evgeny.app.features.viewmodel.ViewModelProviderFactory
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance

val productsFeatureModule = DI.Module {
    bind<ProductsScreenMviHandler>() with factory {
        ProductsScreenMviHandler(
            resourceProvider = instance(),
        )
    }

    bind<ViewModelProviderFactory<ProductsScreenViewModel>>() with factory {
        ViewModelProviderFactory {
            ProductsScreenViewModel(
                mviStateHandler = instance(),
            )
        }
    }
}