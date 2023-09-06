package fetskovich.evgeny.app.features.ui.core.main.products.mapper

import fetskovich.evgeny.app.core.currency.CurrencyFormatter
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.BankCardVariant
import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.cards.CardListItem
import fetskovich.evgeny.entity.card.BankCardWithOffCurrency

class BankCardToListItemMapper(
    private val currencyFormatter: CurrencyFormatter,
) {

    fun map(list: List<BankCardWithOffCurrency>): List<CardListItem> {
        return list.map {
            val bankCard = it.bankCard

            val lastCardNumbers = bankCard.cardNumber.takeLast(4)

            val offCurrency = it.offCurrency?.let { cardAmount ->
                currencyFormatter.format(cardAmount.amount) + " " + cardAmount.currency.currencyValue
            } ?: ""

            CardListItem(
                id = bankCard.id.toString(),
                cardIcon = BankCardVariant.fromBankCardType(bankCard.bankCardType),
                cardName = "DK$lastCardNumbers",
                cardNumber = "●●●● $lastCardNumbers",
                cardBalanceMainCurrency = "${currencyFormatter.format(bankCard.balance)} ${bankCard.cardCurrency.currencyValue}",
                cardBalanceOffCurrency = offCurrency,
            )
        }
    }

    fun mapBankCardsToTotalSum(list: List<BankCardWithOffCurrency>): String? {
        if (list.isEmpty()) {
            return null
        }

        return list
            .asSequence()
            .map { it.bankCard }
            .groupBy { it.cardCurrency }
            .maxBy { it.value.size }
            .value
            .sumOf { it.balance }
            .let(currencyFormatter::format)
    }
}
