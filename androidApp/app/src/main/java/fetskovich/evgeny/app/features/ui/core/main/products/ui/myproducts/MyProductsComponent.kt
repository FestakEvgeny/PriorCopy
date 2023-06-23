package fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.MyProductsState
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductType
import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.cards.CardListComponent
import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.credits.CreditsListComponent
import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.deposits.DepositsListComponent
import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.types.MyProductTypes
import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.types.ProductTypeListItem
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme
import fetskovich.evgeny.recipeskmm.app.R

@Composable
fun MyProductsComponent(
    state: MyProductsState?,
    onAddProductClick: () -> Unit,
    onSortChanged: () -> Unit,
    onChangeProductType: (ProductTypeListItem) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Header(
            onAddProductClick = onAddProductClick
        )

        Spacer(
            modifier = Modifier
                .height(14.dp)
        )

        state?.let {
            MyProductTypes(
                items = state.availableTypes,
                onChangeProductType = onChangeProductType,
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                    )
            )

            Spacer(
                modifier = Modifier
                    .height(14.dp)
            )

            when (state.selectedType) {
                ProductType.CARDS -> {
                    CardListComponent(
                        items = state.cardsList,
                        onSortChange = onSortChanged,
                        modifier = Modifier
                            .fillMaxWidth()

                    )
                }

                ProductType.CREDITS -> CreditsListComponent(
                    items = state.creditsList,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                ProductType.DEPOSITS -> DepositsListComponent(
                    items = state.depositsList,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            }
        } ?: kotlin.run {
            // Display loading
        }

    }
}

@Composable
private fun Header(
    onAddProductClick: () -> Unit,
) {
    val primaryColor = ApplicationTheme.colors.primary

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
            )
    ) {
        Text(
            text = stringResource(id = R.string.products_screen_my_products_title),
            style = MaterialTheme.typography.subtitle1.copy(
                fontSize = 20.sp,
            ),
            modifier = Modifier
                .padding(
                    bottom = 10.dp
                )
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = null,
            modifier = Modifier
                .padding(4.dp)
                .size(12.dp)
                .drawBehind {
                    drawCircle(
                        color = primaryColor,
                        radius = this.size.maxDimension,
                    )
                }
                .clickable(
                    onClick = onAddProductClick,
                )
        )
    }
}

@Preview
@Composable
private fun HeaderPreview() {
    BasicTheme {
        Header(
            onAddProductClick = {}
        )
    }
}