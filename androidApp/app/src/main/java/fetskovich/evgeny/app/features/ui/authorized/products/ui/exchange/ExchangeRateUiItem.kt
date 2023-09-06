package fetskovich.evgeny.app.features.ui.authorized.products.ui.exchange

sealed class ExchangeRateUiItem {

    abstract fun getTitle(): String
    abstract fun getExchangeRateValue(): String

    data class ExchangeItem(
        val monitoringCurrency: String,
        val exchangeRate: String,
    ) : ExchangeRateUiItem() {

        override fun getTitle() = monitoringCurrency
        override fun getExchangeRateValue(): String = exchangeRate
    }

    data object Loading : ExchangeRateUiItem() {
        override fun getTitle(): String = "..."
        override fun getExchangeRateValue(): String = "..."
    }

    data object Error : ExchangeRateUiItem() {
        override fun getTitle(): String = "Failed" // just mock errors
        override fun getExchangeRateValue(): String = "Failed" // just mock errors
    }
}
