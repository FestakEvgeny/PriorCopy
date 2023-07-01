package fetskovich.evgeny.app.features.ui.addbankcard

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import fetskovich.evgeny.app.core.validation.ValidationFieldState
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.AddAnotherBankCardScreenAction
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.AddAnotherBankCardScreenIntent
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.AddAnotherBankCardScreenState
import fetskovich.evgeny.app.features.ui.addbankcard.ui.AddCardFieldsComponent
import fetskovich.evgeny.components.actions.TextActionButton
import fetskovich.evgeny.components.toolbar.BaseToolbar
import fetskovich.evgeny.components.toolbar.items.BackToolbarItem
import fetskovich.evgeny.components.toolbar.items.TitleToolbarItem
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme
import fetskovich.evgeny.recipeskmm.app.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddAnotherBankCardScreen(
    navController: NavController,
    viewModel: AddAnotherBankCardViewModel,
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

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
        onTakeCardPhoto = {},
        onBackClick = {
            navController.popBackStack()
        }
    )

    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycleScope.launch {
            viewModel.actionFlow.collectLatest { action ->
                when (action) {
                    is AddAnotherBankCardScreenAction.NavigateBack -> {
                        navController.popBackStack()
                    }

                    is AddAnotherBankCardScreenAction.DisplayError -> {
                        Toast.makeText(context, action.error, Toast.LENGTH_LONG).show()
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
    onTakeCardPhoto: () -> Unit,
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
                        title = stringResource(id = R.string.add_another_bank_card_title),
                    )
                }
            )
        },
        bottomBar = {
            TextActionButton(
                text = stringResource(id = R.string.add_another_bank_card_add),
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
            Text(
                text = stringResource(id = R.string.add_another_bank_card_message),
                color = ApplicationTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(
                        top = 10.dp,
                        start = 16.dp,
                        end = 16.dp,
                    )
            )

            AddCardFieldsComponent(
                cardNumber = state.cardNumber,
                expirationDate = state.cardExpiration,
                cvv = state.cardCvv,
                onCardNumberChanged = updateCardNumber,
                onExpirationDateChanged = updateCardExpiration,
                onCvvChanged = updateCardCvv,
                onTakeCardFromPhotoClick = onTakeCardPhoto,
                modifier = Modifier
            )
        }
    }
}


@Preview
@Composable
private fun ScreenPreview() {
    BasicTheme(false) {
        Screen(
            state = AddAnotherBankCardScreenState(
                cardNumber = ValidationFieldState.Valid("4916 9894 1200 2311"),
                cardExpiration = ValidationFieldState.Invalid("1421", "Wrong data"),
                cardCvv = ValidationFieldState.Initial()
            ),
            updateCardNumber = {},
            updateCardExpiration = {},
            updateCardCvv = {},
            addCardClick = { },
            onTakeCardPhoto = { },
            onBackClick = {})
    }
}