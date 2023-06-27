package fetskovich.evgeny.app.features.ui.addbankcard

import fetskovich.evgeny.app.features.ui.addbankcard.mvi.AddAnotherBankCardScreenIntent
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.AddAnotherBankCardScreenMviHandler
import fetskovich.evgeny.app.features.viewmodel.BaseViewModel
import fetskovich.evgeny.architecture.mvi.ActionIntent

class AddAnotherBankCardViewModel(
    private val mviStateHandler: AddAnotherBankCardScreenMviHandler,
) : BaseViewModel() {

    val stateFlow = mviStateHandler.stateFlow
    val actionFlow = mviStateHandler.singleAction

    override fun processIntent(intent: ActionIntent) {
        if (intent is AddAnotherBankCardScreenIntent) {
            when (intent) {
                is AddAnotherBankCardScreenIntent.UpdateCardCvv ->
                    mviStateHandler.updateCardCvv(intent.cvv)

                is AddAnotherBankCardScreenIntent.UpdateCardExpiration ->
                    mviStateHandler.updateCardExpiration(intent.expiration)

                is AddAnotherBankCardScreenIntent.UpdateCardNumber ->
                    mviStateHandler.updateCardNumber(intent.number)

                AddAnotherBankCardScreenIntent.AddCardClick -> {
                    TODO()
                }
            }
        }
    }
}