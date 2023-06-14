package fetskovich.evgeny.components.actions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fetskovich.evgeny.presentation.components.R
import fetskovich.evgeny.theme.ApplicationTheme

@Composable
fun ActionButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
  isEnabled: Boolean,
  content: @Composable RowScope.() -> Unit
) {
  Button(
    shape = MaterialTheme.shapes.medium,
    border = BorderStroke(
      width = dimensionResource(id = R.dimen.action_button_border_size),
      color = if (isEnabled) {
        ApplicationTheme.colors.primary
      } else {
        ApplicationTheme.colors.error
      }
    ),
    enabled = isEnabled,
    onClick = onClick,
    modifier = modifier
  ) {
    content()
  }
}

@Composable
fun TextActionButton(
  modifier: Modifier = Modifier,
  text: String,
  isEnabled: Boolean,
  onClick: () -> Unit
) {
  ActionButton(
    modifier = modifier,
    onClick = onClick,
    isEnabled = isEnabled,
    content = {
      Text(
        text = text,
        style = MaterialTheme.typography.body1,
        color = ApplicationTheme.colors.onPrimary,
        modifier = Modifier
          .padding(all = dimensionResource(id = R.dimen.action_button_content_margin))
      )
    }
  )
}

@Preview
@Composable
private fun ActionButtonValidPreview() {
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

@Preview
@Composable
private fun ActionButtonNotValidPreview() {
  TextActionButton(
    modifier = Modifier
      .fillMaxWidth()
      .padding(all = 8.dp),
    text = "Test text",
    onClick = {},
    isEnabled = false
  )
}