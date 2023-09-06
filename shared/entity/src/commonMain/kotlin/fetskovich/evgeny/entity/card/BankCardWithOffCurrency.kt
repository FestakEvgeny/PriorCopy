package fetskovich.evgeny.entity.card

data class BankCardWithOffCurrency (
    val bankCard: BankCard,
    val offCurrency: CardAmount?,
)