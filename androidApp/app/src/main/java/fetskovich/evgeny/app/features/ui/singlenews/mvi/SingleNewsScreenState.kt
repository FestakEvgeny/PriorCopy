package fetskovich.evgeny.app.features.ui.singlenews.mvi

import androidx.compose.ui.graphics.Color
import fetskovich.evgeny.architecture.mvi.ScreenState

data class SingleNewsScreenState(
    val title: String = "",
    val headerColor: Color = Color.Black,
    val htmlDescription: String = "",
): ScreenState