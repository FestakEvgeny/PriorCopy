package fetskovich.evgeny.components.toolbar.items

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fetskovich.evgeny.presentation.components.R
import fetskovich.evgeny.theme.BasicTheme

@Composable
fun SearchToolbarItem(
  onQueryChanged: (text: String) -> Unit,
  query: MutableState<String>,
  modifier: Modifier = Modifier
) {

  Box(
    modifier = modifier
  ) {
    BasicTextField(
      value = query.value,
      onValueChange = {
        query.value = it
        onQueryChanged(it)
      },
      maxLines = 1,
      singleLine = true,
      textStyle = MaterialTheme.typography.body2,
      modifier = Modifier
        .fillMaxWidth()
        .shadow(5.dp, CircleShape)
        .background(Color.White, CircleShape)
        .padding(
          all = dimensionResource(id = R.dimen.toolbar_search_margin)
        )
    )
  }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
private fun SearchToolbarItemPreview() {
  BasicTheme {
    SearchToolbarItem(
      onQueryChanged = {},
      query = mutableStateOf("Test")
    )
  }
}
