package fetskovich.evgeny.networking.api.exchange.model

import fetskovich.evgeny.entity.currency.Currency
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ExchangeRateApiModel(
    val result: String,
    @SerialName("time_last_update_utc")
    val lastUpdate: String,
    @SerialName("time_next_update_utc")
    val nextUpdate: String,
    @SerialName("base_code")
    val baseCode: Currency,
    @SerialName("conversion_rate")
    val conversionRatesModel: ConversionRateApiModel,
)

@Serializable
class ConversionRateApiModel(
    @SerialName("USD")
    val usd: Float,
    @SerialName("BYN")
    val byn: Float,
    @SerialName("EUR")
    val eur: Float,
    @SerialName("PLN")
    val pln: Float,
    @SerialName("RUB")
    val rub: Float,
)