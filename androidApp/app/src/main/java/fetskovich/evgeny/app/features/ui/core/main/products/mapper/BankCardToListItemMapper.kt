package fetskovich.evgeny.app.features.ui.core.main.products.mapper

import fetskovich.evgeny.app.core.currency.CurrencyFormatter
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.BankCardVariant
import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.cards.CardListItem
import fetskovich.evgeny.entity.card.BankCard

class BankCardToListItemMapper(
    private val currencyFormatter: CurrencyFormatter,
) {

    fun map(list: List<BankCard>): List<CardListItem> {
        return list.map {
            val lastCardNumbers = it.cardNumber.takeLast(4)

            CardListItem(
                id = it.id.toString(),
                cardIcon = BankCardVariant.fromBankCardType(it.bankCardType),
                cardName = "DK$lastCardNumbers",
                cardNumber = "●●●● $lastCardNumbers",
                cardBalanceMainCurrency = "${currencyFormatter.format(it.balance)} ${it.cardCurrency.currencyValue}",
                cardBalanceOffCurrency = "${currencyFormatter.format(it.balance * 3)} USD", // TODO calculate depending on the exchange rate
            )
        }
    }

    fun mapBankCardsToTotalSum(list: List<BankCard>): String? {
        if (list.isEmpty()) {
            return null
        }

        return list.groupBy { it.cardCurrency }
            .maxBy { it.value.size }
            .value
            .sumOf { it.balance }
            .let(currencyFormatter::format)
    }
}
