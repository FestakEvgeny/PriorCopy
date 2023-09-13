package fetskovich.evgeny.app.features.ui.unauthorized.exchanges.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ExchangeVariantComponent(
    testIndex: Int,
    modifier: Modifier = Modifier
) {
    Text(text = testIndex.toString())
}