package fetskovich.evgeny.components.toolbar.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import fetskovich.evgeny.presentation.components.R
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme

@Composable
fun IconToolbarItem(
  onClick: () -> Unit,
  imageVector: ImageVector
) {
  Icon(
    imageVector = imageVector,
    contentDescription = null,
    tint = ApplicationTheme.colors.primaryVariant,
    modifier = Modifier
      .size(
        size = dimensionResource(id = R.dimen.toolbar_icon_size)
      )
      .clickable { onClick() }
  )
}

@Preview
@Composable
private fun IconToolbarItemPreview() {
  BasicTheme {
    IconToolbarItem(
      onClick = {},
      imageVector = Filled.Settings
    )
  }
}
