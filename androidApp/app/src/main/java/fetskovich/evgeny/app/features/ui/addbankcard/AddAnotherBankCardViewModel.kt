package fetskovich.evgeny.app.features.ui.addbankcard

import androidx.lifecycle.viewModelScope
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.AddAnotherBankCardScreenAction
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.AddAnotherBankCardScreenIntent
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.AddAnotherBankCardScreenMviHandler
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.BankCardVariant
import fetskovich.evgeny.app.features.viewmodel.BaseViewModel
import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.domain.usecase.card.IdentifyBankCardIntent
import fetskovich.evgeny.domain.usecase.card.IdentifyCardUseCase
import fetskovich.evgeny.domain.usecase.card.ValidateCardCvvIntent
import fetskovich.evgeny.domain.usecase.card.ValidateCardCvvUseCase
import fetskovich.evgeny.domain.usecase.card.ValidateCardExpirationIntent
import fetskovich.evgeny.domain.usecase.card.ValidateCardExpirationUseCase
import fetskovich.evgeny.domain.usecase.card.ValidateCardIntent
import fetskovich.evgeny.domain.usecase.card.ValidateCardNumberUseCase
import kotlinx.coroutines.launch

class AddAnotherBankCardViewModel(
    private val mviStateHandler: AddAnotherBankCardScreenMviHandler,
    private val identifyCardUseCase: IdentifyCardUseCase,
    private val validateCardNumberUseCase: ValidateCardNumberUseCase,
    private val validateCardCvvUseCase: ValidateCardCvvUseCase,
    private val validateCardExpirationUseCase: ValidateCardExpirationUseCase,
    private val coroutinesContextProvider: CoroutinesContextProvider,
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
                    updateCardNumber(intent.number)

                AddAnotherBankCardScreenIntent.AddCardClick -> {
                    handleAddCardClick()
                }
            }
        }
    }

    private fun updateCardNumber(number: String) {
        mviStateHandler.updateCardNumber(number)
        viewModelScope.launch {
            val cardType = identifyCardUseCase.execute(IdentifyBankCardIntent(number)).type
            mviStateHandler.updateCardVariant(
                type = BankCardVariant.fromBankCardType(cardType)
            )
        }
    }

    private fun handleAddCardClick() {
        viewModelScope.launch(coroutinesContextProvider.io) {
            val state = stateFlow.value

            val isCardNumberValid = validateCardNumberUseCase.execute(
                ValidateCardIntent(state.cardNumber.text)
            ).isValid

            val isExpirationValid = validateCardExpirationUseCase.execute(
                ValidateCardExpirationIntent(state.cardExpiration.text)
            ).isValid

            val isCvvValid = validateCardCvvUseCase.execute(
                ValidateCardCvvIntent(state.cardCvv.text)
            ).isValid

            if (isCardNumberValid && isExpirationValid && isCvvValid) {
                // TODO Execute action to save card
                mviStateHandler.processSingleAction(
                    AddAnotherBankCardScreenAction.NavigateBack
                )
            } else {
                mviStateHandler.executeActionOnCardValidation(
                    isCardNumberValid = isCardNumberValid,
                    isCardExpirationValid = isExpirationValid,
                    isCardCvvValid = isCvvValid,
                )
            }
        }
    }
}