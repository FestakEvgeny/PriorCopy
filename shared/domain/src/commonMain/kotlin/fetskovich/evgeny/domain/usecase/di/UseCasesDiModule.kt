package fetskovich.evgeny.domain.usecase.di

import fetskovich.evgeny.domain.usecase.authorization.AuthorizeUserUseCase
import fetskovich.evgeny.domain.usecase.authorization.GetEmailUseCase
import fetskovich.evgeny.domain.usecase.authorization.SaveEmailUseCase
import fetskovich.evgeny.domain.usecase.card.validation.IdentifyCardUseCase
import fetskovich.evgeny.domain.usecase.card.validation.ValidateCardCvvUseCase
import fetskovich.evgeny.domain.usecase.card.validation.ValidateCardExpirationUseCase
import fetskovich.evgeny.domain.usecase.card.validation.ValidateCardNumberUseCase
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance
import org.kodein.di.provider

val useCasesDiModule = DI.Module("UseCaseModule") {
    import(cardModule)
    import(authorizationModule)
}

private val authorizationModule = DI.Module("AuthorizationModule") {
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

// Card
private val cardModule = DI.Module("CardModule") {
    bind<IdentifyCardUseCase>() with provider { IdentifyCardUseCase() }
    bind<ValidateCardCvvUseCase>() with provider { ValidateCardCvvUseCase() }
    bind<ValidateCardExpirationUseCase>() with provider { ValidateCardExpirationUseCase() }
    bind<ValidateCardNumberUseCase>() with provider { ValidateCardNumberUseCase() }
}