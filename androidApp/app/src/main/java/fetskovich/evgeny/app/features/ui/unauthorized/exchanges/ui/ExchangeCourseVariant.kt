package fetskovich.evgeny.app.features.ui.unauthorized.exchanges.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesBankVariant

@Composable
fun ExchangeCourseVariantComponent(variant: ExchangesBankVariant) {
    Text(text = "Course")
}