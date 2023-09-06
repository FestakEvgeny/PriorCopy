package fetskovich.evgeny.app.features.ui.authorized.products.ui.myproducts.types

import fetskovich.evgeny.app.features.ui.authorized.products.mvi.ProductType

data class ProductTypeListItem(
    val id: ProductType,
    val text: String,
    val isSelected: Boolean,
)
