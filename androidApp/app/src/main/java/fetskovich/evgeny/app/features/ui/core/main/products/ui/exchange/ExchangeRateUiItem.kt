package fetskovich.evgeny.app.features.ui.core.main.products.ui.exchange

sealed class ExchangeRateUiItem {

    data class ExchangeItem(
        val monitoringCurrency: String,
        val exchangeRate: String,
    ) : ExchangeRateUiItem()

    data object Loading : ExchangeRateUiItem()

    data object Error : ExchangeRateUiItem()
}
