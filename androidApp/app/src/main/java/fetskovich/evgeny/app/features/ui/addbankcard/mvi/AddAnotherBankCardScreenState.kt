package fetskovich.evgeny.app.features.ui.addbankcard.mvi

import fetskovich.evgeny.app.core.validation.ValidationFieldState
import fetskovich.evgeny.architecture.mvi.ScreenState

data class AddAnotherBankCardScreenState(
    val cardNumber: ValidationFieldState = ValidationFieldState.Initial(),
    val cardExpiration: ValidationFieldState = ValidationFieldState.Initial(),
    val cardCvv: ValidationFieldState = ValidationFieldState.Initial(),
) : ScreenState