package fetskovich.evgeny.app.features.ui.addbankcard.di

import fetskovich.evgeny.app.features.ui.addbankcard.AddAnotherBankCardViewModel
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.AddAnotherBankCardScreenMviHandler
import fetskovich.evgeny.app.features.viewmodel.ViewModelProviderFactory
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance

val addAnotherBankFeatureModule = DI.Module {
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
                coroutinesContextProvider = instance(),
            )
        }
    }
}