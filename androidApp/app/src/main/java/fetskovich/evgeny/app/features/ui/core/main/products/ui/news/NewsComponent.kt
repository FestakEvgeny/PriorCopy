package fetskovich.evgeny.app.features.ui.core.main.products.ui.news

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fetskovich.evgeny.app.features.ui.core.main.products.mapper.NewsToListItemMapper
import fetskovich.evgeny.entity.news.BankNewsShortcut
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme

@Composable
fun NewsComponent(
    news: List<ShortNewsListBaseItem>,
    onNewsClick: (id: String) -> Unit,
    onShowFullNewsClick: () -> Unit,
    modifier: Modifier
) {
    val itemModifier = Modifier
        .width((LocalConfiguration.current.screenWidthDp / 1.7).dp)
        .height(120.dp)
        .clip(RoundedCornerShape(16.dp))

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(
            items = news,
            key = { item ->
                item.id
            }
        ) { item ->
            when (item) {
                is ShortNewsListItem -> ShortNewsComponent(
                    item = item,
                    onNewsClick = onNewsClick,
                    modifier = itemModifier,
                )

                is ShortNewsOtherListItem -> ShortNewsOtherComponent(
                    item = item,
                    onShowFullNewsClick = onShowFullNewsClick,
                    modifier = itemModifier,
                )
            }
        }
    }
}

@Composable
private fun ShortNewsComponent(
    item: ShortNewsListItem,
    onNewsClick: (id: String) -> Unit,
    modifier: Modifier,
) {
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = modifier
            .clickable {
                onNewsClick(item.id)
            }
            .background(item.color)
    ) {

        Text(
            text = item.title,
            style = MaterialTheme.typography.body2.copy(
                fontWeight = FontWeight.Bold
            ),
            color = ApplicationTheme.colors.onPrimary,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Composable
private fun ShortNewsOtherComponent(
    item: ShortNewsOtherListItem,
    onShowFullNewsClick: () -> Unit,
    modifier: Modifier,
) {
    val primaryColor = ApplicationTheme.colors.primary

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(4.dp)
            .clickable(
                onClick = onShowFullNewsClick,
            )
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .padding(8.dp)
                    .drawWithCache {
                        val strokeWidth = 8.dp.value

                        this.onDrawWithContent {
                            val path = Path()
                            path.moveTo(0f, size.height / 2)
                            path.lineTo(size.width, size.height / 2)
                            path.lineTo(size.width / 2, 0f)
                            path.moveTo(size.width, size.height / 2)
                            path.lineTo(size.width / 2, size.height)
                            this.drawPath(
                                path = path,
                                color = primaryColor,
                                style = Stroke(
                                    width = strokeWidth
                                )
                            )
                        }
                    }
            )

            Text(
                text = item.title,
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = ApplicationTheme.colors.primary,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun ShortNewsComponentPreview() {
    val itemModifier = Modifier
        .width((LocalConfiguration.current.screenWidthDp / 1.7).dp)
        .height(120.dp)
        .clip(RoundedCornerShape(16.dp))

    BasicTheme {
        ShortNewsComponent(
            item = ShortNewsListItem(
                id = "1",
                title = "Test title with next following line",
                color = Color.Cyan
            ),
            onNewsClick = {},
            modifier = itemModifier,
        )
    }
}

@Preview
@Composable
private fun ShortNewsOtherComponentPreview() {
    val itemModifier = Modifier
        .width((LocalConfiguration.current.screenWidthDp / 1.7).dp)
        .height(120.dp)
        .clip(RoundedCornerShape(16.dp))

    BasicTheme {
        ShortNewsOtherComponent(
            item = ShortNewsOtherListItem(
                id = "6",
                title = "Смотреть дальше"
            ),
            onShowFullNewsClick = {},
            modifier = itemModifier,
        )
    }
}

@Preview
@Composable
private fun NewsComponentPreview() {
    BasicTheme(darkTheme = false) {
        val news = listOf(
            BankNewsShortcut("1", "Будьте бдительны"),
            BankNewsShortcut("2", "Активируйте свой кредит"),
            BankNewsShortcut("3", "Обновление программы Приведи друга"),
            BankNewsShortcut("4", "Летнее предложение"),
            BankNewsShortcut("5", "Приорбанк в соцсетях"),
        )

        val mappedNews = NewsToListItemMapper().map(news).toMutableList().apply {
            add(ShortNewsOtherListItem("6", "Смотреть дальше"))
        }

        NewsComponent(
            news = mappedNews,
            onNewsClick = {},
            onShowFullNewsClick = { },
            modifier = Modifier.fillMaxWidth()
        )
    }
}