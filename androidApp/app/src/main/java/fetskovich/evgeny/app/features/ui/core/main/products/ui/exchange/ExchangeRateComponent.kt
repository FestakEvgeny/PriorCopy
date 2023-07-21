package fetskovich.evgeny.app.features.ui.core.main.products.ui.exchange

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ExchangeRateComponent(
    item: ExchangeRateItem,
    modifier: Modifier,
) {
    Text(text = item.buy)
}