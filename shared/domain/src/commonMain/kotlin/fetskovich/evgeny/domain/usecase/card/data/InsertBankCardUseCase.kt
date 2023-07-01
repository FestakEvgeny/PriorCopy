package fetskovich.evgeny.domain.usecase.card.data

import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult
import fetskovich.evgeny.architecture.usecase.UseCase
import fetskovich.evgeny.domain.bank.BankCardRepository
import fetskovich.evgeny.entity.card.BankCard

class InsertBankCardUseCase(
    private val bankCardRepository: BankCardRepository,
) : UseCase<InsertBankCardsIntent, InsertBankCardsResult> {

    override suspend fun execute(
        intent: InsertBankCardsIntent
    ): InsertBankCardsResult {
        return try {
            bankCardRepository.createBankCard(intent.bankCard)
            InsertBankCardsResult.Success
        } catch (e: Exception) {
            InsertBankCardsResult.Error(e)
        }
    }
}


data class InsertBankCardsIntent(
    val bankCard: BankCard
) : ActionIntent

sealed class InsertBankCardsResult : IntentResult {

    object Success : InsertBankCardsResult()

    data class Error(
        val error: Throwable
    ) : InsertBankCardsResult()
}
