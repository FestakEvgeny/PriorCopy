package fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.cards

import fetskovich.evgeny.app.features.ui.addbankcard.mvi.BankCardVariant

data class CardListItem(
    val id: String,
    val cardIcon: BankCardVariant,
    val cardName: String,
    val cardNumber: String,
    val cardBalanceMainCurrency: String,
    val cardBalanceOffCurrency: String,
)
