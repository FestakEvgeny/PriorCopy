package fetskovich.evgeny.app.features.ui.authorized.products.ui.news

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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fetskovich.evgeny.app.features.ui.authorized.products.mapper.NewsToListItemMapper
import fetskovich.evgeny.entity.news.BankNewsShortcut
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme
import fetskovich.evgeny.recipeskmm.app.R

@Composable
fun NewsComponent(
    news: List<ShortNewsListBaseItem>,
    onNewsClick: (id: String) -> Unit,
    onShowFullNewsClick: () -> Unit,
    modifier: Modifier
) {
    val itemHeight = remember { 120.dp }
    val itemsPaddings = remember { 16.dp }

    val itemModifier = Modifier
        .width((LocalConfiguration.current.screenWidthDp / 1.7).dp)
        .height(itemHeight)

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(itemsPaddings),
        modifier = modifier
            .fillMaxWidth()
    ) {

        itemsIndexed(
            items = news,
            key = { index, item ->
                item.id
            }
        ) { index, item ->

            if (index == 0) {
                Spacer(
                    modifier = Modifier
                        .height(itemHeight)
                        .width(itemsPaddings)
                )
            }

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

            if (index == news.lastIndex) {
                Spacer(
                    modifier = Modifier
                        .height(itemHeight)
                        .width(itemsPaddings)
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
            .clip(RoundedCornerShape(16.dp))
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
    Card(
        elevation = 6.dp,
        backgroundColor = ApplicationTheme.colors.background,
        modifier = modifier
            .padding(4.dp)
            .clickable(
                onClick = onShowFullNewsClick,
            )
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_arrow),
                contentDescription = null,
                tint = ApplicationTheme.colors.primary,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .size(26.dp)
            )

            Text(
                text = item.title,
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = ApplicationTheme.colors.secondary,
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