package fetskovich.evgeny.domain.bank

import fetskovich.evgeny.entity.card.BankCard
import kotlinx.coroutines.flow.Flow

interface BankCardRepository {

    fun getBankCards(): Flow<List<BankCard>>

    suspend  fun createBankCard(
        card: BankCard
    )
}