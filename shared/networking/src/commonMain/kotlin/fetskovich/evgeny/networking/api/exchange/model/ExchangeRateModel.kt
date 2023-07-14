package fetskovich.evgeny.networking.api.exchange.model

import fetskovich.evgeny.entity.currency.Currency
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ExchangeRateModel(
    val result: String,
    @SerialName("time_last_update_utc")
    val lastUpdate: String,
    @SerialName("base_code")
    val baseCode: Currency,
    @SerialName("conversion_rate")
    val conversionRates: ConversionRate,
)

@Serializable
class ConversionRate(
    @SerialName("USD")
    val usd: Float,
    @SerialName("BYN")
    val byn: Float,
    @SerialName("EUR")
    val eur: Float,
    @SerialName("PLN")
    val pln: Float,
)