package fetskovich.evgeny.app.features.ui.core.main.products.mapper

import fetskovich.evgeny.app.core.currency.CurrencyFormatter
import fetskovich.evgeny.app.features.ui.core.main.products.ui.exchange.ExchangeRateUiItem
import fetskovich.evgeny.entity.currency.Currency
import fetskovich.evgeny.entity.exchange.ExchangeRate

class ExchangeRateToItemMapper(
    private val currencyFormatter: CurrencyFormatter,
) {

    fun mapExchangeRate(
        monitoringCurrency: Currency,
        offCurrency: Currency = Currency.BYN,
        item: ExchangeRate,
    ): ExchangeRateUiItem {
        return ExchangeRateUiItem.ExchangeItem(
            monitoringCurrency = "1 ${monitoringCurrency.currencyValue}",
            exchangeRate = item.conversionRates[offCurrency]?.let(currencyFormatter::format)
                ?: throw IllegalStateException("Conversion rate not found")
        )
    }
}