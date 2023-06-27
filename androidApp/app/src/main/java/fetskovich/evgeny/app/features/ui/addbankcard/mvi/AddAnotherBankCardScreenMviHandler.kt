package fetskovich.evgeny.app.features.ui.addbankcard.mvi

import fetskovich.evgeny.app.core.resources.ResourceProvider
import fetskovich.evgeny.app.core.validation.ValidationFieldState
import fetskovich.evgeny.architecture.mvi.StateHandler

class AddAnotherBankCardScreenMviHandler(
    private val resourceProvider: ResourceProvider,
) : StateHandler<AddAnotherBankCardScreenState, AddAnotherBankCardScreenAction>(
    initialState = AddAnotherBankCardScreenState()
) {

    fun updateCardNumber(text: String) {
        updateState(
            state.copy(
                cardNumber = ValidationFieldState.Valid(text)
            )
        )
    }

    fun updateCardExpiration(text: String) {
        updateState(
            state.copy(
                cardExpiration = ValidationFieldState.Valid(text)
            )
        )
    }

    fun updateCardCvv(text: String) {
        updateState(
            state.copy(
                cardCvv = ValidationFieldState.Valid(text)
            )
        )
    }
}