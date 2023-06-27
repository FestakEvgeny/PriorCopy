package fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.addproduct

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fetskovich.evgeny.components.separators.OvalSeparator
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme
import fetskovich.evgeny.recipeskmm.app.R

private val itemModifier = Modifier
    .padding(
        top = 16.dp
    )

@Composable
fun AddMyProductBottomSheetComponent(
    createCardClick: () -> Unit,
    createCardOfAnotherBankClick: () -> Unit,
    openOnlineDeposit: () -> Unit,
    requestForOpenCredit: () -> Unit,
) {

    val dividerColor = ApplicationTheme.colors.onBackground.copy(
        alpha = 0.1f,
    )

    val textStyle = MaterialTheme.typography.subtitle2

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp,
            )
    ) {

        Spacer(
            modifier = Modifier
                .height(6.dp)
        )

        OvalSeparator(
            color = dividerColor,
            height = 4.dp,
            width = 40.dp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        Text(
            text = stringResource(id = R.string.products_screen_add_product_card),
            style = textStyle,
            modifier = Modifier
                .clickable(
                    onClick = createCardClick
                )
        )

        Divider(
            color = dividerColor,
            modifier = itemModifier
                .fillMaxWidth(),
        )

        Text(
            text = stringResource(id = R.string.products_screen_add_product_card_another_bank),
            style = textStyle,
            modifier = itemModifier
                .clickable(
                    onClick = createCardOfAnotherBankClick
                ),
        )

        Divider(
            color = dividerColor,
            modifier = itemModifier
                .fillMaxWidth(),
        )

        Text(
            text = stringResource(id = R.string.products_screen_add_product_deposit),
            style = textStyle,
            modifier = itemModifier
                .clickable(
                    onClick = openOnlineDeposit
                ),
        )

        Divider(
            color = dividerColor,
            modifier = itemModifier
                .fillMaxWidth(),
        )

        Text(
            text = stringResource(id = R.string.products_screen_add_product_credit),
            style = textStyle,
            modifier = itemModifier
                .clickable(
                    onClick = requestForOpenCredit
                ),
        )

        Spacer(
            modifier = Modifier
                .height(14.dp)
        )
    }
}

@Preview
@Composable
private fun AddMyProductBottomSheetComponentPreview() {
    BasicTheme {
        AddMyProductBottomSheetComponent(
            createCardClick = {},
            createCardOfAnotherBankClick = {},
            openOnlineDeposit = {},
            requestForOpenCredit = {},
        )
    }
}