package fetskovich.evgeny.entity.card

import fetskovich.evgeny.entity.currency.Currency

data class CardAmount (
    val currency: Currency,
    val amount: Double,
)