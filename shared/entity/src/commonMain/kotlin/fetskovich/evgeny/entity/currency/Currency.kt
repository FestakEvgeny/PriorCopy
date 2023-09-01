package fetskovich.evgeny.entity.currency

// In ISO-4127 code
enum class Currency(val currencyValue: String) {
    USD("USD"),
    BYN("BYN"),
    EUR("EUR"),
    PLN("PLN"),
    RUB("RUB");

    companion object {

        fun fromString(text: String): Currency {
            return try {
                Currency.valueOf(text)
            } catch (e: Exception) {
                USD
            }
        }
    }
}