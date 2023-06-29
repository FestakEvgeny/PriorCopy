package fetskovich.evgeny.app.features.ui.core.main.login.di

import fetskovich.evgeny.app.features.ui.core.main.login.LoginScreenViewModel
import fetskovich.evgeny.app.features.ui.core.main.login.mvi.LoginScreenMviHandler
import fetskovich.evgeny.app.features.viewmodel.ViewModelProviderFactory
import fetskovich.evgeny.domain.usecase.authorization.di.authorizationDomainModule
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance

val loginScreenFeatureModule = DI.Module("Login") {
    import(authorizationDomainModule)

    bind<LoginScreenMviHandler>() with factory {
        LoginScreenMviHandler(
            resourceProvider = instance()
        )
    }

    bind<ViewModelProviderFactory<LoginScreenViewModel>>() with factory {
        ViewModelProviderFactory {
            LoginScreenViewModel(
                mviHandler = instance(),
                getEmailUseCase = instance(),
                authorizeUseCase = instance(),
                coroutinesContextProvider = instance(),
            )
        }
    }
}