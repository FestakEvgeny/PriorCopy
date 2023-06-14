package fetskovich.evgeny.components.toolbar.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import fetskovich.evgeny.theme.ApplicationTheme
import fetskovich.evgeny.theme.BasicTheme

@Composable
fun TitleToolbarItem(
  title: String,
  modifier: Modifier = Modifier,
  onClick: (() -> Unit)? = null
) {
  Text(
    style = MaterialTheme.typography.body1,
    text = title,
    color = ApplicationTheme.colors.toolbarItemColor,
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