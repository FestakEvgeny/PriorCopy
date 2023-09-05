package fetskovich.evgeny.app.features.ui.core.main.products.ui.exchange

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme
import fetskovich.evgeny.recipeskmm.app.R

@Composable
fun ExchangeRateComponent(
    exchangeRate: ExchangeRateUiItem,
    totalSum: String?,
    modifier: Modifier,
) {
    Row(
        modifier = modifier
            .height(140.dp)
    ) {

        ExchangeRateElement(
            item = exchangeRate,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )

        TotalSumComponent(
            totalSum = totalSum,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )
    }
}

@Composable
private fun ExchangeRateElement(
    modifier: Modifier = Modifier,
    item: ExchangeRateUiItem
) {
    Card(
        elevation = 4.dp,
        backgroundColor = ApplicationTheme.colors.background,
        modifier = modifier
            .padding(
                start = 16.dp,
                top = 6.dp,
                bottom = 6.dp,
                end = 6.dp,
            )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_usd),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(20.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Column {
                    Text(
                        text = stringResource(id = R.string.products_screen_exchange_title),
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 12.sp,
                        ),
                        color = ApplicationTheme.colors.secondaryVariant,
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = item.getTitle(),
                        style = MaterialTheme.typography.subtitle2,
                        color = ApplicationTheme.colors.primaryVariant,
                    )
                }

            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = item.getExchangeRateValue(),
                    style = MaterialTheme.typography.subtitle2,
                    color = ApplicationTheme.colors.primaryVariant,
                )

                if (item is ExchangeRateUiItem.ExchangeItem)
                    Image(
                        painter = painterResource(id = R.drawable.ic_exchange_rate_up),
                        contentDescription = null,
                        modifier = Modifier
                            .size(
                                height = 12.dp,
                                width = 8.dp,
                            )
                    )
            }

            Spacer(
                modifier = Modifier
                    .height(4.dp)
            )

            Text(
                text = stringResource(id = R.string.products_screen_exchange_buy),
                style = MaterialTheme.typography.body2.copy(
                    fontSize = 12.sp,
                ),
                color = ApplicationTheme.colors.secondaryVariant,
            )
        }
    }
}

@Composable
private fun TotalSumComponent(
    totalSum: String?,
    modifier: Modifier,
) {

    Card(
        elevation = 4.dp,
        backgroundColor = ApplicationTheme.colors.background,
        modifier = modifier
            .padding(
                end = 16.dp,
                top = 6.dp,
                bottom = 6.dp,
                start = 6.dp,
            ),
    ) {

        Box {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {

                Text(
                    text = stringResource(id = R.string.products_screen_total_sum_title),
                    style = MaterialTheme.typography.body2.copy(
                        fontSize = 12.sp,
                    ),
                    color = ApplicationTheme.colors.secondaryVariant,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(id = R.string.products_screen_total_sum_all_checks),
                    style = MaterialTheme.typography.subtitle2,
                    color = ApplicationTheme.colors.primaryVariant,
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = totalSum ?: "...",
                    style = MaterialTheme.typography.subtitle2,
                    color = ApplicationTheme.colors.primaryVariant,
                )
            }

            val secondaryColor = ApplicationTheme.colors.secondaryVariant
            val secondaryColorAlpha = remember { secondaryColor.copy(alpha = 0.3f) }

            // TODO Move to Right
            Spacer(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxHeight()
                    .width(50.dp)
                    .padding(
                        end = 16.dp,
                        top = 20.dp,
                        bottom = 20.dp,
                    )
                    .drawWithCache {
                        val strokeWidth = 6.dp.value

                        this.onDrawWithContent {
                            val path = Path()
                            path.moveTo(size.width, 0f)
                            path.lineTo(0f, 0f)
                            path.lineTo(size.width / 2, size.height / 2)
                            path.lineTo(0f, size.height)
                            path.lineTo(size.width, size.height)
                            this.drawPath(
                                path = path,
                                color = secondaryColorAlpha,
                                style = Stroke(
                                    width = strokeWidth
                                )
                            )
                        }
                    }
            )

        }
    }
}

@Preview
@Composable
private fun ExchangeRateElementErrorPreview() {
    BasicTheme {
        ExchangeRateElement(
            item = ExchangeRateUiItem.Error
        )
    }
}

@Preview
@Composable
private fun ExchangeRateElementLoadingPreview() {
    BasicTheme {
        ExchangeRateElement(
            item = ExchangeRateUiItem.Loading
        )
    }
}

@Preview
@Composable
private fun ExchangeRateElementDataPreview() {
    BasicTheme {
        ExchangeRateElement(
            item = ExchangeRateUiItem.ExchangeItem(
                monitoringCurrency = "1 USD",
                exchangeRate = "3,2050"
            )
        )
    }
}