package fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mapper

import fetskovich.evgeny.app.core.currency.CurrencyFormatter
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesBankVariant
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesCardsVariant
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesCashVariant
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesOnlineVariant
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesPrice
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesVariant
import fetskovich.evgeny.entity.currency.Currency
import fetskovich.evgeny.entity.exchange.ExchangeRate

class ExchangesRatesMapper(
    private val currencyFormatter: CurrencyFormatter,
) {

    fun mapToOnline(rate: ExchangeRate): ExchangesOnlineVariant {
          return ExchangesOnlineVariant(
            exchanges = createExchangeVariantState(rate),
            conversions = createConversions(rate),
            actualDate = "Актуальны с 11:45 12 сент. 2023" // TODO Calculate date
        )
    }

    fun mapToCard(rate: ExchangeRate): ExchangesCardsVariant {
        return ExchangesCardsVariant(
            exchanges = createExchangeVariantState(rate),
            conversions = createConversions(rate),
            actualDate = "Актуальны с 11:45 12 сент. 2023" // TODO Calculate date
        )
    }

    fun mapToCash(rate: ExchangeRate): ExchangesCashVariant {
        return ExchangesCashVariant(
            exchanges = createExchangeVariantState(rate),
            conversions = createConversions(rate),
            actualDate = "Актуальны с 11:45 12 сент. 2023" // TODO Calculate date
        )
    }

    fun mapToBank(rate: ExchangeRate): ExchangesBankVariant {
        return ExchangesBankVariant() // TODO Implement
    }

    private fun createExchangeVariantState(rate: ExchangeRate): List<ExchangesVariant> {
        return listOf(
            ExchangesVariant(
                name = "1 ${Currency.USD.currencyValue}",
                buyPrice = ExchangesPrice(
                    price = rate.conversionRates[Currency.BYN]?.let(currencyFormatter::format)
                        ?: "...",
                    isPositive = true,
                ),
                sellPrice = ExchangesPrice(
                    price = rate.conversionRates[Currency.BYN]?.let(currencyFormatter::format)
                        ?: "...",
                    isPositive = false,
                ),
            ),
            ExchangesVariant(
                name = "1 ${Currency.EUR.currencyValue}",
                buyPrice = createExchangePrice(
                    rate = rate,
                    monitoringCurrency = Currency.EUR,
                ),
                sellPrice = createExchangePrice(
                    rate = rate,
                    monitoringCurrency = Currency.EUR,
                ),
            ),
            ExchangesVariant(
                name = "100 ${Currency.RUB.currencyValue}",
                buyPrice = createExchangePrice(
                    rate = rate,
                    monitoringCurrency = Currency.RUB,
                    moneyAmount = 100.0,
                ),
                sellPrice = createExchangePrice(
                    rate = rate,
                    monitoringCurrency = Currency.RUB,
                    moneyAmount = 100.0,
                ),
            )
        )
    }

    private fun createConversions(
        rate: ExchangeRate,
    ): List<ExchangesVariant> {
        return listOf(
            ExchangesVariant(
                name = "EUR/USD",
                buyPrice = ExchangesPrice(
                    price = ((rate.conversionRates[Currency.USD] ?: 0.0) / (rate.conversionRates[Currency.EUR] ?: 1.0)).let(currencyFormatter::format),
                    isPositive = false
                ),
                sellPrice = ExchangesPrice(
                    price = ((rate.conversionRates[Currency.USD] ?: 0.0) / (rate.conversionRates[Currency.EUR] ?: 1.0)).let(currencyFormatter::format),
                    isPositive = false
                ),
            ),
            ExchangesVariant(
                name = "USD/RUB",
                buyPrice = ExchangesPrice(
                    price = ((rate.conversionRates[Currency.RUB] ?: 0.0) / (rate.conversionRates[Currency.USD] ?: 1.0)).let(currencyFormatter::format),
                    isPositive = false
                ),
                sellPrice = ExchangesPrice(
                    price = ((rate.conversionRates[Currency.RUB] ?: 0.0) / (rate.conversionRates[Currency.USD] ?: 1.0)).let(currencyFormatter::format),
                    isPositive = false
                ),
            ),
            ExchangesVariant(
                name = "EUR/RUB",
                buyPrice = ExchangesPrice(
                    price = ((rate.conversionRates[Currency.RUB] ?: 0.0) / (rate.conversionRates[Currency.EUR] ?: 1.0)).let(currencyFormatter::format),
                    isPositive = false
                ),
                sellPrice = ExchangesPrice(
                    price = ((rate.conversionRates[Currency.RUB] ?: 0.0) / (rate.conversionRates[Currency.EUR] ?: 1.0)).let(currencyFormatter::format),
                    isPositive = false
                ),
            )
        )
    }

    private fun createExchangePrice(
        rate: ExchangeRate,
        monitoringCurrency: Currency,
        moneyAmount: Double = 1.0,
    ): ExchangesPrice {
        return ExchangesPrice(
            price = ((rate.conversionRates[Currency.BYN]
                ?: 0.0) / (rate.conversionRates[monitoringCurrency] ?: 1.0) * moneyAmount).let(
                currencyFormatter::format
            ),
            isPositive = kotlin.random.Random.nextBoolean(),
        )
    }
}