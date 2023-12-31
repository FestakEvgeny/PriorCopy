package fetskovich.evgeny.components.actions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fetskovich.evgeny.presentation.components.R
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme

@Composable
fun ActionButton(
    onClick: () -> Unit,
    isError: Boolean,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    withBorder: Boolean = false,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        shape = MaterialTheme.shapes.medium,
        border = if (withBorder) {
            BorderStroke(
                width = dimensionResource(id = R.dimen.action_button_border_size),
                color = if (isError) {
                    ApplicationTheme.colors.primary
                } else {
                    ApplicationTheme.colors.error
                }
            )
        } else {
            null
        },
        enabled = isEnabled,
        onClick = onClick,
        modifier = modifier
            .width(40.dp)
    ) {
        content()
    }
}

@Composable
fun TextActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isError: Boolean = false,
) {
    ActionButton(
        modifier = modifier,
        onClick = onClick,
        isError = isError,
        isEnabled = isEnabled,
        content = {
            Text(
                text = text.uppercase(),
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Bold,
                ),
                color = ApplicationTheme.colors.primaryVariant,
                modifier = Modifier
                    .padding(all = dimensionResource(id = R.dimen.action_button_content_margin))
            )
        }
    )
}

@Preview
@Composable
private fun ActionButtonErrorPreview() {
    BasicTheme {
        TextActionButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    all = dimensionResource(id = R.dimen.action_button_text_margin)
                ),
            text = "Test text",
            onClick = {},
            isEnabled = true,
            isError = true,
        )
    }
}

@Preview
@Composable
private fun ActionButtonValidPreview() {
    BasicTheme {
        TextActionButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    all = dimensionResource(id = R.dimen.action_button_text_margin)
                ),
            text = "Test text",
            onClick = {},
            isEnabled = true
        )
    }
}

@Preview
@Composable
private fun ActionButtonNotValidPreview() {
    BasicTheme {
        TextActionButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            text = "Test text",
            onClick = {},
            isEnabled = false
        )
    }
}