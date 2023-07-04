package fetskovich.evgeny.entity.card

import fetskovich.evgeny.entity.currency.Currency

data class BankCard(
    val id: Long?,
    val bankCardType: BankCardType,
    val cardNumber: String,
    val expirationDate: Long,
    val cvv: Long,
    val cardCurrency: Currency,
    val balance: Double,
) {

    companion object {

        const val NOT_EXISTS_DB_ID = -1L
    }
}
