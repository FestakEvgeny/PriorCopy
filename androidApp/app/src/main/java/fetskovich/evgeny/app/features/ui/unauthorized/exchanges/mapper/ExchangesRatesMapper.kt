package fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mapper

import fetskovich.evgeny.app.core.currency.CurrencyFormatter
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesBankVariant
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesCardsVariant
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesCashVariant
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesOnlineVariant
import fetskovich.evgeny.entity.exchange.ExchangeRate

class ExchangesRatesMapper (
    private val currencyFormatter: CurrencyFormatter,
) {

    fun mapToOnline(rate: ExchangeRate): ExchangesOnlineVariant {
        TODO()
    }

    fun mapToCard(rate: ExchangeRate): ExchangesCardsVariant {
        TODO()
    }

    fun mapToCash(rate: ExchangeRate): ExchangesCashVariant {
        TODO()
    }

    fun mapToBank(rate: ExchangeRate): ExchangesBankVariant {
        TODO()
    }
}