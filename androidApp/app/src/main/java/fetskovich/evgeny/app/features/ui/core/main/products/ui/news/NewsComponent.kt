package fetskovich.evgeny.app.features.ui.core.main.products.ui.news

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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
        .height(60.dp)
        .clip(RoundedCornerShape(16.dp))

    LazyRow(
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
                    modifier = modifier,
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
                .padding(8.dp)
        )
    }
}

@Composable
private fun ShortNewsOtherComponent(
    item: ShortNewsOtherListItem,
    onShowFullNewsClick: () -> Unit,
    modifier: Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(4.dp)
            .clickable(
                onClick = onShowFullNewsClick,
            )
    ) {

        Column {
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
        .height(60.dp)
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
private fun NewsComponentPreview() {
    BasicTheme(darkTheme = false) {
        val news = listOf(
            BankNewsShortcut("1", "Будьте бдительны"),
            BankNewsShortcut("2", "Активируйте свой кредит"),
            BankNewsShortcut("3", "Обновление программы Приведи друга"),
            BankNewsShortcut("4", "Летнее предложение"),
            BankNewsShortcut("5", "Приорбанк в соцсетях"),
        )

        val mappedNews = NewsToListItemMapper().map(news)

        NewsComponent(
            news = mappedNews,
            onNewsClick = {},
            onShowFullNewsClick = { },
            modifier = Modifier.fillMaxWidth()
        )
    }
}