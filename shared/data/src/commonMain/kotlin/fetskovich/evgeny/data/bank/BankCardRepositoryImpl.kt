package fetskovich.evgeny.data.bank

import fetskovich.evgeny.data.database.dao.BankCardDao
import fetskovich.evgeny.domain.bank.BankCardRepository

class BankCardRepositoryImpl(
    private val bankCardDao: BankCardDao
): BankCardRepository {

    override fun createBank() {

    }
}