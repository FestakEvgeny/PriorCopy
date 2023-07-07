package fetskovich.evgeny.app.features.ui.singlenews.ui.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import kotlin.random.Random

val SINGLE_NEWS_EXPANDED_TOOLBAR_HEIGHT = 200.dp

@Composable
fun SingleNewsExpandedToolbar(
    title: String
) {
    val bgColor = remember {
        Color(
            red = Random.nextInt(0, 255),
            green = Random.nextInt(0, 255),
            blue = Random.nextInt(0, 255),
            alpha = Random.nextInt(0, 255),
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(SINGLE_NEWS_EXPANDED_TOOLBAR_HEIGHT)
            .background(bgColor)
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 26.dp,
            ),
        contentAlignment = Alignment.BottomStart,
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            color = ApplicationTheme.colors.onPrimary,
        )

    }
}