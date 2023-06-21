package fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.types

import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductType

data class ProductTypeListItem(
    val id: ProductType,
    val text: String,
    val isSelected: Boolean,
)
