package fetskovich.evgeny.app.features.ui.addbankcard.mvi

import fetskovich.evgeny.architecture.mvi.SingleAction

sealed class AddAnotherBankCardScreenAction : SingleAction {

    object NavigateBack : AddAnotherBankCardScreenAction()

    data class DisplayError(
        val error: String
    ): AddAnotherBankCardScreenAction()
}