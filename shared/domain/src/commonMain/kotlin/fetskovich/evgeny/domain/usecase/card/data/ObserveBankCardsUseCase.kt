package fetskovich.evgeny.domain.usecase.card.data

import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult
import fetskovich.evgeny.architecture.usecase.ReactiveUseCase
import fetskovich.evgeny.domain.bank.BankCardRepository
import fetskovich.evgeny.entity.card.BankCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveBankCardsUseCase(
    private val repository: BankCardRepository
) : ReactiveUseCase<ObserveBankCardsIntent, ObserverBankCardsResult> {

    override fun execute(
        intent: ObserveBankCardsIntent
    ): Flow<ObserverBankCardsResult> {
        return repository.getBankCards()
            .map {
                if (it.isEmpty()) {
                    ObserverBankCardsResult.CardsNotFound
                } else {
                    ObserverBankCardsResult.ReceivedCards(it)
                }
            }
    }
}

object ObserveBankCardsIntent : ActionIntent

sealed class ObserverBankCardsResult : IntentResult {

    object CardsNotFound : ObserverBankCardsResult()

    data class ReceivedCards(val data: List<BankCard>) : ObserverBankCardsResult()
}