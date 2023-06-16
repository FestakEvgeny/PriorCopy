package fetskovich.evgeny.app.features.ui.core.main.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fetskovich.evgeny.app.features.ui.core.main.login.mvi.LoginScreenIntent
import fetskovich.evgeny.app.features.ui.core.main.login.mvi.LoginScreenState
import fetskovich.evgeny.components.actions.TextActionButton
import fetskovich.evgeny.components.text.BaseTextField
import fetskovich.evgeny.components.text.PasswordTextField
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.recipeskmm.app.R

@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    Screen(
        state = state,
        onEmailTextChanged = {
            viewModel.processIntent(LoginScreenIntent.UpdateEmail(it))
        },
        onPasswordTextChanged = {
            viewModel.processIntent(LoginScreenIntent.UpdatePassword(it))
        },
        onReversePasswordClick = {
            viewModel.processIntent(LoginScreenIntent.ReversePasswordVisibility)
        },
        onLoginButtonClick = {
            viewModel.processIntent(LoginScreenIntent.LoginUser)
        },
    )
}

@Composable
private fun Screen(
    state: LoginScreenState,
    onEmailTextChanged: (String) -> Unit,
    onPasswordTextChanged: (String) -> Unit,
    onReversePasswordClick: () -> Unit,
    onLoginButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        Spacer(
            modifier = Modifier
                .height(50.dp)
        )

        BaseTextField(
            text = state.userEmail,
            hintText = stringResource(id = R.string.login_screen_email),
            onTextChanged = onEmailTextChanged,
            modifier = Modifier
                .fillMaxWidth()
        )
        if (!state.emailErrorMessage.isNullOrEmpty()) {
            Text(
                text = state.emailErrorMessage,
                color = ApplicationTheme.colors.error,
            )
        }

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        PasswordField(
            password = state.userPassword,
            isPasswordVisible = state.isPasswordVisible,
            onPasswordTextChanged = onPasswordTextChanged,
            onReversePasswordClick = onReversePasswordClick,
        )
        if (!state.passwordErrorMessage.isNullOrEmpty()) {
            Text(
                text = state.passwordErrorMessage,
                color = ApplicationTheme.colors.error,
            )
        }

        Spacer(
            modifier = Modifier
                .height(40.dp)
        )

        TextActionButton(
            text = stringResource(id = R.string.login_screen_enter),
            onClick = onLoginButtonClick,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        Text(
            text = stringResource(id = R.string.login_screen_forgot_data),
            color = ApplicationTheme.colors.baseTextColor,
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )

        Text(
            text = stringResource(id = R.string.login_screen_sign_up),
            color = ApplicationTheme.colors.baseTextColor,
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun PasswordField(
    password: String,
    isPasswordVisible: Boolean,
    onPasswordTextChanged: (String) -> Unit,
    onReversePasswordClick: () -> Unit,
) {
    PasswordTextField(
        text = password,
        hintText = "Password",
        isVisualTransformationEnabled = isPasswordVisible,
        onTextChanged = onPasswordTextChanged,
        trailingIcon = {
            val icon = if (isPasswordVisible) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }

            IconButton(
                onClick = onReversePasswordClick,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
    )
}