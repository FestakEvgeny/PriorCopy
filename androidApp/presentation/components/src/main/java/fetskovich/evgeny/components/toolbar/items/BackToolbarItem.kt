package fetskovich.evgeny.components.toolbar.items

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme

@Composable
fun BackToolbarItem(
  onClick: () -> Unit
) {
  Icon(
    imageVector = Icons.Filled.ArrowBack,
    contentDescription = null,
    tint = ApplicationTheme.colors.toolbarItemColor,
    modifier = Modifier
      .clickable { onClick() }
  )
}

@Composable
@Preview
private fun BackToolbarItemPreview() {
  BasicTheme {
    BackToolbarItem(
      onClick = {}
    )
  }
}
