package fetskovich.evgeny.app.features.ui.addbankcard.mvi

import fetskovich.evgeny.architecture.mvi.ActionIntent

sealed class AddAnotherBankCardScreenIntent : ActionIntent {

    data class UpdateCardNumber(
       val number: String
    ) : AddAnotherBankCardScreenIntent()

    data class UpdateCardExpiration(
        val expiration: String
    ) : AddAnotherBankCardScreenIntent()

    data class UpdateCardCvv(
        val cvv: String
    ) : AddAnotherBankCardScreenIntent()

    object AddCardClick: AddAnotherBankCardScreenIntent()
}