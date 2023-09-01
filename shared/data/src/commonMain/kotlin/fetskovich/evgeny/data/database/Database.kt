package fetskovich.evgeny.data.database

import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import fetskovich.evgeny.data.database.dao.BankCardDao
import fetskovich.evgeny.data.database.dao.BankCardDaoImpl
import fetskovich.evgeny.data.database.dao.ExchangeRateDao
import fetskovich.evgeny.data.database.dao.ExchangeRateDaoImpl

internal class Database(
    databaseDriverFactory: DatabaseDriverFactory,
    coroutinesContextProvider: CoroutinesContextProvider,
) {

    private val database = AppDatabase(databaseDriverFactory.createDriver())

    val bankCardDao: BankCardDao = BankCardDaoImpl(
        dbQuery = database.bankCardApiQueries,
        coroutinesContextProvider = coroutinesContextProvider,
    )

    val exchangeRatedDao: ExchangeRateDao = ExchangeRateDaoImpl(
        dbQuery = database.exchangeRateApiQueries,
    )
}