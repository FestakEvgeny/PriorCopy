package fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.MyProductsState
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductType
import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.cards.CardListComponent
import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.types.MyProductTypes
import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.types.ProductTypeListItem
import fetskovich.evgeny.presentation.theme.ApplicationTheme
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

        state?.let {
            MyProductTypes(
                items = state.availableTypes,
                onChangeProductType = onChangeProductType,
                modifier = Modifier
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

                ProductType.CREDITS -> Text(text = "Credits")
                ProductType.DEPOSITS -> Text(text = "Deposits")
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
    val baseTextColor = ApplicationTheme.colors.primaryVariant

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
            style = MaterialTheme.typography.body1,
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .size(32.dp)
                .drawBehind {
                    drawCircle(
                        color = baseTextColor,
                        radius = this.size.maxDimension,
                    )
                }
                .clickable(
                    onClick = onAddProductClick,
                )
        )
    }
}
