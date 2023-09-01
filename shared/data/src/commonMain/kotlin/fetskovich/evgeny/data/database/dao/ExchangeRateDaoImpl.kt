package fetskovich.evgeny.data.database.dao

import fetskovich.evgeny.data.database.ExchangeRateApiQueries
import fetskovich.evgeny.data.database.ExchangeRateModel

interface ExchangeRateDao {

    fun createExchangeRate(model: ExchangeRateModel)
    fun getActualExchangeRate(): ExchangeRateModel?
}

class ExchangeRateDaoImpl(
    private val dbQuery: ExchangeRateApiQueries
) : ExchangeRateDao {

    override fun createExchangeRate(model: ExchangeRateModel) {
        dbQuery.transaction {
            dbQuery.insertExchangeRateModel(model)
        }
    }

    override fun getActualExchangeRate(): ExchangeRateModel? {
        return dbQuery.getExchangeRateModels().executeAsOneOrNull()
    }
}