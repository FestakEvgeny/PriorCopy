package fetskovich.evgeny.components.separators

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fetskovich.evgeny.presentation.theme.BasicTheme

@Composable
fun OvalSeparator(
    color: Color,
    height: Dp,
    width: Dp,
    modifier: Modifier,
) {

    Box(
        modifier = modifier
        .height(height)
        .width(width)
            .clip(RoundedCornerShape(22.dp))
            .background(color)
    )
}

@Preview
@Composable
private fun OvalSeparatorPreview() {
    BasicTheme {
        OvalSeparator(
            color = Color.Cyan,
            height = 4.dp,
            width = 70.dp,
            modifier = Modifier
        )
    }
}