package fetskovich.evgeny.app.features.ui.unauthorized.login.di

import fetskovich.evgeny.app.features.ui.unauthorized.login.LoginScreenViewModel
import fetskovich.evgeny.app.features.ui.unauthorized.login.mvi.LoginScreenMviHandler
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