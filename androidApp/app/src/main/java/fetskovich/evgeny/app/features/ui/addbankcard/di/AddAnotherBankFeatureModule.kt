package fetskovich.evgeny.app.features.ui.addbankcard.di

import fetskovich.evgeny.app.features.ui.addbankcard.AddAnotherBankCardViewModel
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.AddAnotherBankCardScreenMviHandler
import fetskovich.evgeny.app.features.viewmodel.ViewModelProviderFactory
import fetskovich.evgeny.domain.usecase.card.data.InsertBankCardUseCase
import fetskovich.evgeny.domain.usecase.card.expiration.di.expirationTimestampDomainModule
import fetskovich.evgeny.domain.usecase.card.validation.di.verifyCardDomainModule
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance

val addAnotherBankCardFeatureModule = DI.Module("AddAnotherBankCard") {
    import(verifyCardDomainModule)
    import(expirationTimestampDomainModule)

    bind<InsertBankCardUseCase>() with factory {
        InsertBankCardUseCase(
            bankCardRepository = instance(),
        )
    }

    bind<AddAnotherBankCardScreenMviHandler>() with factory {
        AddAnotherBankCardScreenMviHandler(
            resourceProvider = instance()
        )
    }

    bind<ViewModelProviderFactory<AddAnotherBankCardViewModel>>() with factory {
        ViewModelProviderFactory {
            AddAnotherBankCardViewModel(
                mviStateHandler = instance(),
                identifyCardUseCase = instance(),
                validateCardCvvUseCase = instance(),
                validateCardNumberUseCase = instance(),
                validateCardExpirationUseCase = instance(),
                expirationDateToTimestampUseCase = instance(),
                insertBankCardUseCase = instance(),
                resourceProvider = instance(),
                coroutinesContextProvider = instance(),
            )
        }
    }
}