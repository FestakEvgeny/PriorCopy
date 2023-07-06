package fetskovich.evgeny.app.features.ui.singlenews.di

import fetskovich.evgeny.app.features.ui.singlenews.SingleNewsViewModel
import fetskovich.evgeny.app.features.ui.singlenews.mvi.SingleNewsScreenMviHandler
import fetskovich.evgeny.app.features.viewmodel.ViewModelProviderFactory
import fetskovich.evgeny.domain.usecase.news.di.newsDiModule
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance

val singleNewsFeatureModule = DI.Module("SingleNewsFeature") {
    import(newsDiModule)

    bind<SingleNewsScreenMviHandler>() with factory { SingleNewsScreenMviHandler() }

    bind<ViewModelProviderFactory<SingleNewsViewModel>>() with factory {
        ViewModelProviderFactory {
            SingleNewsViewModel(
                newsId = "2",
                mviHandler = instance(),
                getSingleNewsUseCase = instance(),
                coroutinesContextProvider = instance(),
            )
        }
    }
}