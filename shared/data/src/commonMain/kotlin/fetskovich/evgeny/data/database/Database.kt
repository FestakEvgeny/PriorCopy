package fetskovich.evgeny.data.database

import fetskovich.evgeny.data.database.dao.BankCardDaoImpl

internal class Database(
    databaseDriverFactory: DatabaseDriverFactory
) {

    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    val bankCardDao: BankCardDaoImpl = BankCardDaoImpl(
        dbQuery = dbQuery,
    )
}