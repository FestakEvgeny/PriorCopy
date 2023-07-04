package fetskovich.evgeny.data.database.dao

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import fetskovich.evgeny.data.database.AppDatabaseQueries
import fetskovich.evgeny.data.database.BankCardModel
import fetskovich.evgeny.entity.card.BankCard
import kotlinx.coroutines.flow.Flow

interface BankCardDao {

    fun createBankCardModel(model: BankCardModel)
    fun getAllBankCardModels(): List<BankCardModel>
    fun observeBankCardModels() : Flow<List<BankCardModel>>
    fun clearTable()
}

class BankCardDaoImpl(
    private val dbQuery: AppDatabaseQueries,
    private val coroutinesContextProvider: CoroutinesContextProvider,
) : BankCardDao {

    override fun clearTable() {
        dbQuery.transaction {
            dbQuery.removeAllBankCards()
        }
    }

    override fun observeBankCardModels(): Flow<List<BankCardModel>> {
        return dbQuery.selectedAlLBankCards().asFlow().mapToList(coroutinesContextProvider.io)
    }

    override fun getAllBankCardModels(): List<BankCardModel> {
        return dbQuery.selectedAlLBankCards().executeAsList()
    }

    override fun createBankCardModel(model: BankCardModel) {
        // Workaround for non availability to make model id as not null, investigate it
        val id = if (model.id == BankCard.NOT_EXISTS_DB_ID) {
            null
        } else {
            model.id
        }

        dbQuery.transaction {
            dbQuery.insertBankCardModel(
                id = id,
                bankCardType = model.bankCardType,
                cardNumber = model.cardNumber,
                expirationDate = model.expirationDate,
                cvv = model.cvv,
                cardCurrency = model.cardCurrency,
                balance = model.balance,
            )
        }
    }
}