package fetskovich.evgeny.components.text

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import fetskovich.evgeny.presentation.theme.ApplicationTheme
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
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    TextField(
        value = text,
        onValueChange = onTextChanged,
        textStyle = MaterialTheme.typography.body2,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = ApplicationTheme.colors.primary,
            unfocusedIndicatorColor = if (isError) {
                ApplicationTheme.colors.error
            } else {
                ApplicationTheme.colors.primaryVariant.copy(
                    alpha = 0.3f
                )
            },
            backgroundColor = Color.Black.copy(
                alpha = 0.05f
            )
        ),
        label = {
            hintText?.let { hint ->
                Text(
                    text = hint,
                    color = if (isError) {
                        ApplicationTheme.colors.error
                    } else {
                        ApplicationTheme.colors.baseTextColor.copy(
                            alpha = 0.7f,
                        )
                    },
                )
            }
        },
        isError = isError,
        trailingIcon = trailingIcon,
        modifier = modifier,
    )
}

@Composable
fun PasswordTextField(
    text: String,
    hintText: String?,
    onTextChanged: (text: String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    singleLine: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardActions: KeyboardActions = KeyboardActions(),
    isVisualTransformationEnabled: Boolean = false,
) {
    BaseTextField(
        text = text,
        hintText = hintText,
        onTextChanged = onTextChanged,
        modifier = modifier,
        isError = isError,
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (isVisualTransformationEnabled) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        keyboardActions = keyboardActions,
        trailingIcon = trailingIcon,
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
