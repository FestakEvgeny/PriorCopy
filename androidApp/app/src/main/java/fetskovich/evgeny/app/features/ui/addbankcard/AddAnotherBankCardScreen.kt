package fetskovich.evgeny.app.features.ui.addbankcard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.AddAnotherBankCardScreenAction
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.AddAnotherBankCardScreenIntent
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.AddAnotherBankCardScreenState
import fetskovich.evgeny.components.actions.TextActionButton
import fetskovich.evgeny.components.toolbar.BaseToolbar
import fetskovich.evgeny.components.toolbar.items.BackToolbarItem
import fetskovich.evgeny.components.toolbar.items.TitleToolbarItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddAnotherBankCardScreen(
    navController: NavController,
    viewModel: AddAnotherBankCardViewModel,
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    Screen(
        state = state,
        updateCardNumber = {
            viewModel.processIntent(AddAnotherBankCardScreenIntent.UpdateCardNumber(it))
        },
        updateCardExpiration = {
            viewModel.processIntent(AddAnotherBankCardScreenIntent.UpdateCardExpiration(it))
        },
        updateCardCvv = {
            viewModel.processIntent(AddAnotherBankCardScreenIntent.UpdateCardCvv(it))
        },
        addCardClick = {
            viewModel.processIntent(AddAnotherBankCardScreenIntent.AddCardClick)
        },
        onBackClick = {
            navController.popBackStack()
        }
    )

    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycleScope.launch {
            viewModel.actionFlow.collectLatest { action ->
                when (action) {
                    is AddAnotherBankCardScreenAction.NavigateBack -> {
                        // TODO Display simple toast
                    }
                }
            }
        }
    }

}

@Composable
private fun Screen(
    state: AddAnotherBankCardScreenState,
    updateCardNumber: (String) -> Unit,
    updateCardExpiration: (String) -> Unit,
    updateCardCvv: (String) -> Unit,
    addCardClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            BaseToolbar(
                leftComponent = {
                    BackToolbarItem(
                        onClick = onBackClick
                    )
                },
                centerComponent = {
                    TitleToolbarItem(
                        title = "Карта другого банка",
                    )
                }
            )
        },
        bottomBar = {
            TextActionButton(
                text = "Добавить",
                onClick = addCardClick,
                modifier = Modifier
                    .padding(
                        all = 16.dp
                    )
                    .fillMaxWidth()
            )
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
        ) {
            Text(text = "Bank account page")
        }
    }
}