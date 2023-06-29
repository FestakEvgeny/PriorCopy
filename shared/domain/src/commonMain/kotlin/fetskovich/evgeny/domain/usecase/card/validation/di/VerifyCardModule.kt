package fetskovich.evgeny.domain.usecase.card.validation.di

import fetskovich.evgeny.domain.usecase.card.validation.IdentifyCardUseCase
import fetskovich.evgeny.domain.usecase.card.validation.ValidateCardCvvUseCase
import fetskovich.evgeny.domain.usecase.card.validation.ValidateCardExpirationUseCase
import fetskovich.evgeny.domain.usecase.card.validation.ValidateCardNumberUseCase
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.provider

val verifyCardDomainModule = DI.Module ("VerifyCardDomain") {
    bind<IdentifyCardUseCase>() with provider { IdentifyCardUseCase() }
    bind<ValidateCardCvvUseCase>() with provider { ValidateCardCvvUseCase() }
    bind<ValidateCardExpirationUseCase>() with provider { ValidateCardExpirationUseCase() }
    bind<ValidateCardNumberUseCase>() with provider { ValidateCardNumberUseCase() }
}
