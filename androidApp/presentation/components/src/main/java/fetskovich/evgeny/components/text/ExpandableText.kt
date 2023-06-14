package fetskovich.evgeny.components.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import fetskovich.evgeny.presentation.components.R
import fetskovich.evgeny.theme.ApplicationTheme

private const val DEFAULT_VISIBLE_LINES = 2

// TODO Implement expand/collapse logic
private const val EXPAND_COLLAPSE_LENGTH_CONDITION = 30

@Composable
fun ExpandableText(
  text: String,
  modifier: Modifier = Modifier,
  lines: Int = DEFAULT_VISIBLE_LINES,
) {
  var isExpanded by remember { mutableStateOf(false) }

  Column(
    modifier = modifier
      .fillMaxWidth()
  ) {
    Text(
      text = text,
      style = MaterialTheme.typography.subtitle2,
      color = ApplicationTheme.colors.primary,
      maxLines = if (isExpanded) {
        Int.MAX_VALUE
      } else {
        lines
      },

      overflow = TextOverflow.Ellipsis
    )

    if (text.length > EXPAND_COLLAPSE_LENGTH_CONDITION) {
      Text(
        text = if (isExpanded) {
          stringResource(id = R.string.collapse)
        } else {
          stringResource(id = R.string.expand)
        },
        style = MaterialTheme.typography.body2,
        color = ApplicationTheme.colors.secondary,
        modifier = Modifier
          .clickable {
            isExpanded = !isExpanded
          }
      )
    }
  }
}
