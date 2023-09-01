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
    @SerialName("conversion_rates")
    val conversionRatesModel: ConversionRateApiModel,
)

@Serializable
class ConversionRateApiModel(
    @SerialName("USD")
    val usd: Double,
    @SerialName("BYN")
    val byn: Double,
    @SerialName("EUR")
    val eur: Double,
    @SerialName("PLN")
    val pln: Double,
    @SerialName("RUB")
    val rub: Double,
)