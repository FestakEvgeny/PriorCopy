package fetskovich.evgeny.entity.exchange

import fetskovich.evgeny.entity.currency.Currency

data class ExchangeRate(
    val id: Long,
    val nextUpdate: Long,
    val lastUpdate: Long,
    val baseCurrency: Currency,
    val conversionRates: Map<Currency, Double>,
)
