package fetskovich.evgeny.components.text

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fetskovich.evgeny.presentation.theme.BasicTheme

@Suppress("LongParameterList")
@Composable
fun BaseTextField(
  text: String,
  hintText: String?,
  onTextChanged: (text: String) -> Unit,
  modifier: Modifier = Modifier,
  isError: Boolean = false,
  singleLine: Boolean = false,
  keyboardOptions: KeyboardOptions = KeyboardOptions(),
  keyboardActions: KeyboardActions = KeyboardActions(),
) {
  OutlinedTextField(
    value = text,
    onValueChange = { newText ->
      onTextChanged(newText)
    },
    textStyle = MaterialTheme.typography.body2,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    singleLine = singleLine,
    label = {
      hintText?.let { hint ->
        Text(text = hint)
      }
    },
    isError = isError,
    modifier = modifier,
  )
}

@Preview
@Composable
private fun BaseTextFieldSimplePreview() {
  BasicTheme {
    var name by remember { mutableStateOf("") }

    BaseTextField(
      text = name,
      hintText = "Test hint",
      onTextChanged = {
        name = it
      }
    )
  }
}

@Preview
@Composable
private fun BaseTextFieldErrorPreview() {
  BasicTheme {
    var name by remember { mutableStateOf("") }

    BaseTextField(
      text = name,
      hintText = "Test hint",
      isError = true,
      onTextChanged = {
        name = it
      }
    )
  }
}

@Preview
@Composable
private fun BaseTextFieldHintPreview() {
  BasicTheme {
    BaseTextField(
      text = "",
      hintText = "Test hint",
      isError = true,
      onTextChanged = {
        // do nothing
      }
    )
  }
}
