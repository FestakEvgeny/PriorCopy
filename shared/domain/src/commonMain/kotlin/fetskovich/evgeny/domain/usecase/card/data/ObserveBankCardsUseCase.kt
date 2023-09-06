package fetskovich.evgeny.domain.usecase.card.data

import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult
import fetskovich.evgeny.architecture.usecase.ReactiveUseCase
import fetskovich.evgeny.domain.bank.BankCardRepository
import fetskovich.evgeny.domain.exchange.ExchangeRateRepository
import fetskovich.evgeny.entity.card.BankCard
import fetskovich.evgeny.entity.card.BankCardWithOffCurrency
import fetskovich.evgeny.entity.card.CardAmount
import fetskovich.evgeny.entity.currency.Currency
import fetskovich.evgeny.entity.exchange.ExchangeRate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class ObserveBankCardsUseCase(
    private val repository: BankCardRepository,
    private val exchangeRateRepository: ExchangeRateRepository,
) : ReactiveUseCase<ObserveBankCardsIntent, ObserverBankCardsResult> {

    override fun execute(
        intent: ObserveBankCardsIntent
    ): Flow<ObserverBankCardsResult> {
        return combine(
            repository.getBankCards(),
            exchangeRateRepository.getExchangeRate(intent.formattedOffCurrency)
        ) { cards, exchange ->
            mapBankCardWithExchangeRate(
                cards = cards,
                exchange = exchange,
                offCurrency = intent.formattedOffCurrency,
            )
        }.map {
            ObserverBankCardsResult(it)
        }
    }

    private fun mapBankCardWithExchangeRate(
        cards: List<BankCard>,
        exchange: ExchangeRate?,
        offCurrency: Currency,
    ): List<BankCardWithOffCurrency> {
        return cards.map { bankCard ->
            val conversionRate = exchange?.conversionRates?.get(bankCard.cardCurrency)
            val exchangedConversion = conversionRate?.let {
                val amount = bankCard.balance / it
                CardAmount(
                    currency = offCurrency,
                    amount = amount,
                )
            }

            BankCardWithOffCurrency(
                bankCard = bankCard,
                offCurrency = exchangedConversion,
            )
        }
    }
}

data class ObserveBankCardsIntent(
    val formattedOffCurrency: Currency,
) : ActionIntent

data class ObserverBankCardsResult(val data: List<BankCardWithOffCurrency>) : IntentResult
