package fetskovich.evgeny.data.database.dao

import fetskovich.evgeny.data.database.AppDatabaseQueries
import fetskovich.evgeny.data.database.BankCardModel

interface BankCardDao {

    fun createBankCardModel(model: BankCardModel)
    fun getAllBankCardModels(): List<BankCardModel>
    fun clearTable()
}

class BankCardDaoImpl(
    private val dbQuery: AppDatabaseQueries,
) : BankCardDao {

    override fun clearTable() {
        dbQuery.transaction {
            dbQuery.removeAllBankCards()
        }
    }

    override fun getAllBankCardModels(): List<BankCardModel> {
        return dbQuery.selectedAlLBankCards().executeAsList()
    }

    override fun createBankCardModel(model: BankCardModel) {
        dbQuery.transaction {
            dbQuery.insertBankCardModel(
                id = model.id,
                bankCardType = model.bankCardType,
                cardNumber = model.cardNumber,
                expirationDate = model.expirationDate,
                cvv = model.cvv,
            )
        }
    }
}