package fetskovich.evgeny.app.features.ui.addbankcard

import androidx.lifecycle.viewModelScope
import fetskovich.evgeny.app.core.resources.ResourceProvider
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.AddAnotherBankCardScreenAction
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.AddAnotherBankCardScreenIntent
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.AddAnotherBankCardScreenMviHandler
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.AddAnotherBankCardScreenState
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.BankCardVariant
import fetskovich.evgeny.app.features.viewmodel.BaseViewModel
import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.domain.usecase.card.data.InsertBankCardUseCase
import fetskovich.evgeny.domain.usecase.card.data.InsertBankCardsIntent
import fetskovich.evgeny.domain.usecase.card.data.InsertBankCardsResult
import fetskovich.evgeny.domain.usecase.card.expiration.ExpirationDateToTimestampIntent
import fetskovich.evgeny.domain.usecase.card.expiration.ExpirationDateToTimestampResult
import fetskovich.evgeny.domain.usecase.card.expiration.ExpirationDateToTimestampUseCase
import fetskovich.evgeny.domain.usecase.card.validation.IdentifyBankCardIntent
import fetskovich.evgeny.domain.usecase.card.validation.IdentifyCardUseCase
import fetskovich.evgeny.domain.usecase.card.validation.ValidateCardCvvIntent
import fetskovich.evgeny.domain.usecase.card.validation.ValidateCardCvvUseCase
import fetskovich.evgeny.domain.usecase.card.validation.ValidateCardExpirationIntent
import fetskovich.evgeny.domain.usecase.card.validation.ValidateCardExpirationUseCase
import fetskovich.evgeny.domain.usecase.card.validation.ValidateCardIntent
import fetskovich.evgeny.domain.usecase.card.validation.ValidateCardNumberUseCase
import fetskovich.evgeny.entity.card.BankCard
import fetskovich.evgeny.entity.currency.Currency
import fetskovich.evgeny.recipeskmm.app.R
import kotlinx.coroutines.launch
import kotlin.random.Random

class AddAnotherBankCardViewModel(
    private val mviStateHandler: AddAnotherBankCardScreenMviHandler,
    private val identifyCardUseCase: IdentifyCardUseCase,
    private val validateCardNumberUseCase: ValidateCardNumberUseCase,
    private val validateCardCvvUseCase: ValidateCardCvvUseCase,
    private val validateCardExpirationUseCase: ValidateCardExpirationUseCase,
    private val insertBankCardUseCase: InsertBankCardUseCase,
    private val expirationDateToTimestampUseCase: ExpirationDateToTimestampUseCase,
    private val coroutinesContextProvider: CoroutinesContextProvider,
    private val resourceProvider: ResourceProvider,
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
                parseDataAndInsertBankCard(state)
            } else {
                mviStateHandler.executeActionOnCardValidation(
                    isCardNumberValid = isCardNumberValid,
                    isCardExpirationValid = isExpirationValid,
                    isCardCvvValid = isCvvValid,
                )
            }
        }
    }

    private suspend fun parseDataAndInsertBankCard(
        state: AddAnotherBankCardScreenState
    ) {
        val expirationDate = state.cardExpiration.text
        val expirationDateTimestampResult = expirationDateToTimestampUseCase.execute(
            ExpirationDateToTimestampIntent(
                expirationDate = expirationDate,
            )
        )

        when (expirationDateTimestampResult) {
            ExpirationDateToTimestampResult.InvalidInput -> {
                mviStateHandler.processSingleAction(
                    AddAnotherBankCardScreenAction.DisplayError(
                        resourceProvider.provideString(R.string.add_another_bank_card_expiration_invalid_format) // add error message
                    )
                )
            }

            is ExpirationDateToTimestampResult.Success -> {
                insertBankCard(
                    state = state,
                    expirationTimestamp = expirationDateTimestampResult.timestamp,
                )
            }
        }
    }

    private suspend fun insertBankCard(
        state: AddAnotherBankCardScreenState,
        expirationTimestamp: Long,
    ) {
        val result = insertBankCardUseCase.execute(
            InsertBankCardsIntent(
                bankCard = BankCard(
                    id = 0L,
                    bankCardType = BankCardVariant.toBankType(state.cardVariant),
                    cardNumber = state.cardNumber.text,
                    expirationDate = expirationTimestamp,
                    cvv = state.cardCvv.text.toLong(),
                    cardCurrency = Currency.BYN,
                    balance = Random.nextDouble(500.0, 100000.0),
                )
            )
        )

        when (result) {
            is InsertBankCardsResult.Error -> {
                mviStateHandler.processSingleAction(
                    AddAnotherBankCardScreenAction.DisplayError(result.error.message ?: "")
                )
            }

            InsertBankCardsResult.Success -> {
                mviStateHandler.processSingleAction(AddAnotherBankCardScreenAction.NavigateBack)
            }
        }
    }
}