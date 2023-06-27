package fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.types

import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductType
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme

@Composable
fun MyProductTypes(
    items: List<ProductTypeListItem>,
    onChangeProductType: (ProductTypeListItem) -> Unit,
    modifier: Modifier,
) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        items(
            items = items,
            key = { item ->
                item.id
            }
        ) { item ->
            ProductComponent(
                item = item,
                onChangeProductType = onChangeProductType,
            )
        }
    }
}

@Composable
private fun ProductComponent(
    item: ProductTypeListItem,
    onChangeProductType: (ProductTypeListItem) -> Unit,
) {
    Text(
        text = item.text,
        style = MaterialTheme.typography.body2,
        color = if (item.isSelected) {
            ApplicationTheme.colors.onPrimary
        } else {
            ApplicationTheme.colors.primaryVariant
        },
        textAlign = TextAlign.Center,
        modifier = Modifier
            .clickable(
                onClick = {
                    onChangeProductType(item)
                },
            )
            .graphicsLayer {
                shape = RoundedCornerShape(12.dp)
                clip = true
            }
            .background(
                if (item.isSelected) {
                    ApplicationTheme.colors.primaryVariant
                } else {
                    ApplicationTheme.colors.secondary
                }
            )
            .padding(
                horizontal = 14.dp,
                vertical = 6.dp,
            ),
    )
}

@Preview
@Composable
private fun ProductComponentPreview() {
    BasicTheme {
        ProductComponent(
            item = ProductTypeListItem(
                id = ProductType.DEPOSITS,
                text = "Deposits",
                isSelected = false,
            ),
            onChangeProductType = {}
        )
    }
}

@Preview
@Composable
private fun MyProductTypesPreview() {
    BasicTheme {
        MyProductTypes(
            items = listOf(
                ProductTypeListItem(
                    id = ProductType.CARDS,
                    text = "Cards",
                    isSelected = false,
                ),
                ProductTypeListItem(
                    id = ProductType.CREDITS,
                    text = "Credits",
                    isSelected = true,
                ), ProductTypeListItem(
                    id = ProductType.DEPOSITS,
                    text = "Deposits",
                    isSelected = false,
                )
            ),
            onChangeProductType = { item -> },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}