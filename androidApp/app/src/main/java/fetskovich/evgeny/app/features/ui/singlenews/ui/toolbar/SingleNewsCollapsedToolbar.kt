package fetskovich.evgeny.app.features.ui.singlenews.ui.toolbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fetskovich.evgeny.presentation.theme.ApplicationTheme

val SINGLE_NEWS_COLLAPSED_TOOLBAR_HEIGHT = 56.dp

@Composable
fun SingleNewsCollapsedToolbar(
    title: String,
    isCollapsed: Boolean,
    modifier: Modifier = Modifier,
) {
    val color: Color by animateColorAsState(
        if (isCollapsed) {
            MaterialTheme.colors.background
        } else {
            Color.Transparent
        }
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(SINGLE_NEWS_COLLAPSED_TOOLBAR_HEIGHT)
            .background(color)
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp,
            )
    ) {

        if (isCollapsed) {
            Text(
                text = title,
                style = MaterialTheme.typography.body1,
                color = ApplicationTheme.colors.primaryVariant,
            )
        }
    }
}