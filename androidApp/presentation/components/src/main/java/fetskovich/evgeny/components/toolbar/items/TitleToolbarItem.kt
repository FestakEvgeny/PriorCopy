package fetskovich.evgeny.components.toolbar.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme

@Composable
fun TitleToolbarItem(
  title: String,
  modifier: Modifier = Modifier,
  onClick: (() -> Unit)? = null
) {
  Text(
    style = MaterialTheme.typography.subtitle2,
    text = title,
    color = ApplicationTheme.colors.primaryVariant,
    modifier = modifier
      .clickable { onClick?.invoke() }
  )
}

@Composable
@Preview
private fun TitleToolbarItemPreview() {
  BasicTheme {
    TitleToolbarItem(
      title = "Test title",
      modifier = Modifier
        .background(
          color = Color.Red
        ),
      onClick = {}
    )
  }
}