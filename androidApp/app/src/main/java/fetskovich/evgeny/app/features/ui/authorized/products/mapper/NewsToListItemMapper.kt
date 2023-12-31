package fetskovich.evgeny.app.features.ui.authorized.products.mapper

import androidx.compose.ui.graphics.Color
import fetskovich.evgeny.app.features.ui.authorized.products.ui.news.ShortNewsListBaseItem
import fetskovich.evgeny.app.features.ui.authorized.products.ui.news.ShortNewsListItem
import fetskovich.evgeny.entity.news.BankNewsShortcut
import kotlin.random.Random

class NewsToListItemMapper {

    fun map(list: List<BankNewsShortcut>): List<ShortNewsListBaseItem> {
        return list.map {
            ShortNewsListItem(
                id = it.id,
                title = it.title,
                color = Color(
                    red = Random.nextInt(0, 255),
                    green = Random.nextInt(0, 255),
                    blue = Random.nextInt(0, 255),
                    alpha = Random.nextInt(0, 255),
                )
            )
        }
    }
}