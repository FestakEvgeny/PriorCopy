package fetskovich.evgeny.app.features.ui.core.main.products.ui.news

import androidx.compose.ui.graphics.Color

interface ShortNewsListBaseItem {
    val id: String
    val title: String
}

data class ShortNewsListItem(
    override val id: String,
    override val title: String,
    val color: Color,
) : ShortNewsListBaseItem

data class ShortNewsOtherListItem(
    override val id: String,
    override val title: String,
) : ShortNewsListBaseItem
