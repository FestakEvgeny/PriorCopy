package fetskovich.evgeny.domain.exchange

import fetskovich.evgeny.entity.currency.Currency
import fetskovich.evgeny.entity.exchange.ExchangeRate
import kotlinx.coroutines.flow.Flow

interface ExchangeRateRepository {

    fun getExchangeRate(
        currency: Currency
    ): Flow<ExchangeRate?>
}
