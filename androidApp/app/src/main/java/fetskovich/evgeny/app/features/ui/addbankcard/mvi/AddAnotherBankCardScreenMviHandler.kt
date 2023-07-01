package fetskovich.evgeny.app.features.ui.addbankcard.mvi

import fetskovich.evgeny.app.core.resources.ResourceProvider
import fetskovich.evgeny.app.core.validation.ValidationFieldState
import fetskovich.evgeny.architecture.mvi.StateHandler
import fetskovich.evgeny.recipeskmm.app.R

class AddAnotherBankCardScreenMviHandler(
    private val resourceProvider: ResourceProvider,
) : StateHandler<AddAnotherBankCardScreenState, AddAnotherBankCardScreenAction>(
    initialState = AddAnotherBankCardScreenState(
        cardNumber = ValidationFieldState.Valid("4916989602113451"),
        cardExpiration = ValidationFieldState.Valid("0227"),
        cardCvv = ValidationFieldState.Valid("312"),
        cardVariant = BankCardVariant.VISA,
    )
) {

    fun updateCardNumber(text: String) {
        updateState(
            state.copy(
                cardNumber = ValidationFieldState.Valid(text)
            )
        )
    }

    fun updateCardVariant(type: BankCardVariant) {
        updateState(
            state.copy(
                cardVariant = type
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

    fun executeActionOnCardValidation(
        isCardNumberValid: Boolean,
        isCardExpirationValid: Boolean,
        isCardCvvValid: Boolean
    ) {
        val cardNumberField = if (isCardNumberValid) {
            ValidationFieldState.Valid(state.cardNumber.text)
        } else {
            ValidationFieldState.Invalid(
                state.cardNumber.text,
                resourceProvider.provideString(R.string.add_another_bank_card_card_number_invalid)
            )
        }

        val cardExpirationField = if (isCardExpirationValid) {
            ValidationFieldState.Valid(state.cardExpiration.text)
        } else {
            ValidationFieldState.Invalid(
                state.cardExpiration.text,
                resourceProvider.provideString(R.string.add_another_bank_card_expiration_invalid)
            )
        }

        val cardCvvField = if (isCardCvvValid) {
            ValidationFieldState.Valid(state.cardCvv.text)
        } else {
            ValidationFieldState.Invalid(
                state.cardCvv.text,
                resourceProvider.provideString(R.string.add_another_bank_card_cvv_invalid)
            )
        }

        updateState(
            state.copy(
                cardNumber = cardNumberField,
                cardExpiration = cardExpirationField,
                cardCvv = cardCvvField,
            )
        )
    }
}