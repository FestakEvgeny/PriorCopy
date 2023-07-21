package fetskovich.evgeny.app.features.ui.core.main.products.mapper

import fetskovich.evgeny.app.features.ui.core.main.products.ui.exchange.ExchangeRateItem
import fetskovich.evgeny.entity.currency.Currency
import fetskovich.evgeny.entity.exchange.ExchangeRate

class ExchangeRateToItemMapper {

    fun mapExchangeRate(
        watchCurrency: Currency,
        toCurrency: Currency,
        item: ExchangeRate,
    ): ExchangeRateItem {
        return ExchangeRateItem(
            monitoringCurrency = watchCurrency.currencyValue,
            buy = item.conversionRates[toCurrency]?.toString().orEmpty(),
            sell = item.conversionRates[toCurrency]?.toString().orEmpty()
        )
    }
}