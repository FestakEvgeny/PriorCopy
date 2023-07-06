package fetskovich.evgeny.app.features.ui.singlenews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import fetskovich.evgeny.app.features.ui.singlenews.mvi.SingleNewsScreenState

@Composable
fun SingleNewsScreen(
    navController: NavController,
    viewModel: SingleNewsViewModel,
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    Screen(
        state = state,
    )
}

@Composable
private fun Screen(state: SingleNewsScreenState) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            Text(text = state.title)

            Text(text = state.htmlDescription)
        }
    }
}