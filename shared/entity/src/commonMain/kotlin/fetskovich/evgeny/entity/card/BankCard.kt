package fetskovich.evgeny.entity.card

data class BankCard(
    val id: Long,
    val bankCardType: BankCardType,
    val cardNumber: String,
    val expirationDate: Long,
    val cvv: Long,
)
