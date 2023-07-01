package fetskovich.evgeny.data.database

import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import fetskovich.evgeny.data.database.dao.BankCardDaoImpl

internal class Database(
    databaseDriverFactory: DatabaseDriverFactory,
    coroutinesContextProvider: CoroutinesContextProvider,
) {

    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    val bankCardDao: BankCardDaoImpl = BankCardDaoImpl(
        dbQuery = dbQuery,
        coroutinesContextProvider = coroutinesContextProvider,
    )
}