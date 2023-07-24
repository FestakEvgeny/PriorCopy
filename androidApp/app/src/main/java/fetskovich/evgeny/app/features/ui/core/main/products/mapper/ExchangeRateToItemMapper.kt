package fetskovich.evgeny.app.features.ui.core.main.products.mapper

import fetskovich.evgeny.app.features.ui.core.main.products.ui.exchange.ExchangeRateItem
import fetskovich.evgeny.entity.currency.Currency
import fetskovich.evgeny.entity.exchange.ExchangeRate

class ExchangeRateToItemMapper {

    fun mapExchangeRate(
        basicCurrency: Currency,
        offCurrency: Currency,
        item: ExchangeRate,
    ): ExchangeRateItem {
        return ExchangeRateItem(
            monitoringCurrency = basicCurrency.currencyValue,
            buy = item.conversionRates[offCurrency]?.toString().orEmpty(),
            sell = item.conversionRates[offCurrency]?.toString().orEmpty()
        )
    }
}