package fetskovich.evgeny.app.core.currency

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class CoreCurrencyFormatter: CurrencyFormatter {

    private val formatSymbols = DecimalFormatSymbols(Locale.getDefault())
    private val formatter = DecimalFormat("#,####", formatSymbols).apply {
        maximumFractionDigits = 4
    }

    override fun format(currency: Double): String {
        return formatter.format(currency)
    }

    companion object {

        const val DI_TAG = "Core"
    }
}