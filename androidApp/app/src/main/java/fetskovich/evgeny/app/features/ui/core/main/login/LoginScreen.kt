package fetskovich.evgeny.app.features.ui.core.main.login

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    Text(text = "Login. Hi "+state.latestEmail)
}