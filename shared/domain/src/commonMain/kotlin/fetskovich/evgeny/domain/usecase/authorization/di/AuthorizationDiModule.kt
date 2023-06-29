package fetskovich.evgeny.domain.usecase.authorization.di

import fetskovich.evgeny.domain.usecase.authorization.AuthorizeUserUseCase
import fetskovich.evgeny.domain.usecase.authorization.GetEmailUseCase
import fetskovich.evgeny.domain.usecase.authorization.SaveEmailUseCase
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance

val authorizationDomainModule = DI.Module("AuthorizationDomain") {
    bind<GetEmailUseCase>() with factory {
        GetEmailUseCase(
            userSettingsStorage = instance(),
            coroutinesContextProvider = instance(),
        )
    }

    bind<SaveEmailUseCase>() with factory {
        SaveEmailUseCase(
            settingsStorage = instance()
        )
    }

    bind<AuthorizeUserUseCase>() with factory {
        AuthorizeUserUseCase(
            authorizationRepository = instance(),
            saveEmailUseCase = instance(),
            coroutinesContextProvider = instance(),
        )
    }
}