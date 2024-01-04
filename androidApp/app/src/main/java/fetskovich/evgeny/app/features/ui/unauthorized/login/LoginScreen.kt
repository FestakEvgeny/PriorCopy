package fetskovich.evgeny.app.features.ui.unauthorized.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import fetskovich.evgeny.app.features.ui.authorized.bottomnav.api.AuthorizedMainScreenNavigation
import fetskovich.evgeny.app.features.ui.unauthorized.bottomnav.api.UnauthorizedMainScreenNavigation
import fetskovich.evgeny.app.features.ui.unauthorized.login.mvi.LoginScreenAction
import fetskovich.evgeny.app.features.ui.unauthorized.login.mvi.LoginScreenIntent
import fetskovich.evgeny.app.features.ui.unauthorized.login.mvi.LoginScreenState
import fetskovich.evgeny.components.actions.TextActionButton
import fetskovich.evgeny.components.text.BaseTextField
import fetskovich.evgeny.components.text.PasswordTextField
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme
import fetskovich.evgeny.recipeskmm.app.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel,
    navController: NavHostController,
    parentNavController: NavHostController?,
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    val loginTextChangedLambda = remember {
        { login: String ->
            viewModel.processIntent(LoginScreenIntent.UpdateEmail(login))
        }
    }

    val passwordTextChangedLambda = remember {
        { password: String ->
            viewModel.processIntent(LoginScreenIntent.UpdatePassword(password))
        }
    }

    val reversePasswordClickLambda = remember {
        {
            viewModel.processIntent(LoginScreenIntent.ReversePasswordVisibility)
        }
    }

    val loginButtonClickLambda = remember {
        {
            viewModel.processIntent(LoginScreenIntent.LoginUser)
        }
    }

    Screen(
        state = state,
        onEmailTextChanged = loginTextChangedLambda,
        onPasswordTextChanged = passwordTextChangedLambda,
        onReversePasswordClick = reversePasswordClickLambda,
        onLoginButtonClick = loginButtonClickLambda,
    )

    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycleScope.launch {
            // TODO flowWithLifecycle
            viewModel.actionFlow.collectLatest { action ->
                when (action) {
                    is LoginScreenAction.DisplayError -> {
                        // TODO Display simple toast
                    }

                    LoginScreenAction.NavigateToProducts -> {
                        parentNavController?.navigate(AuthorizedMainScreenNavigation.route) {
                            popUpTo(UnauthorizedMainScreenNavigation.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
        }
    }
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
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        Spacer(
            modifier = Modifier
                .height(60.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.login_screen_logo),
                style = MaterialTheme.typography.subtitle1.copy(
                    fontStyle = FontStyle.Italic,
                    fontSize = 24.sp,
                ),
                color = ApplicationTheme.colors.primaryVariant,
            )
        }

        Spacer(
            modifier = Modifier
                .height(40.dp)
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
                style = MaterialTheme.typography.body2,
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
                style = MaterialTheme.typography.body2,
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
                .height(30.dp)
        )

        Text(
            text = stringResource(id = R.string.login_screen_forgot_data),
            color = ApplicationTheme.colors.primaryVariant,
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(id = R.string.login_screen_sign_up),
            color = ApplicationTheme.colors.primaryVariant,
            style = MaterialTheme.typography.body2.copy(
                fontWeight = FontWeight.Bold,
            ),
        )

        Spacer(modifier = Modifier.height(10.dp))
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
        hintText = stringResource(id = R.string.login_screen_password),
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

@Preview
@Composable
private fun ScreenPreview() {
    BasicTheme {
        Screen(
            state = LoginScreenState(),
            onEmailTextChanged = {},
            onPasswordTextChanged = {},
            onReversePasswordClick = { },
            onLoginButtonClick = {},
        )
    }
}