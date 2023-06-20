package fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.cards

import androidx.annotation.DrawableRes

data class CardListItem(
    val id: String,
    @DrawableRes val cardIcon: Int,
    val cardName: String,
    val cardNumber: String,
    val cardBalanceMainCurrency: String,
    val cardBalanceOffCurrency: String,
)
