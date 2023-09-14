package fetskovich.evgeny.app.features.ui.unauthorized.exchanges.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangeVariantState
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesOnlineVariant
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesPrice
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesVariant
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme
import fetskovich.evgeny.recipeskmm.app.R

@Composable
fun ExchangeVariantComponent(
    variants: ExchangeVariantState,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 14.dp,
                end = 14.dp,
            )
            .verticalScroll(ScrollState(0))
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = variants.actualDate,
            style = MaterialTheme.typography.h3,
            color = ApplicationTheme.colors.secondaryVariant,
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (variants.exchanges.isNotEmpty()) {
            ExchangeHeaderComponent()
            Spacer(modifier = Modifier.height(14.dp))
            ExchangeVariantsCardComponent(
                exchanges = variants.exchanges,
            )
        }

        if (variants.conversions.isNotEmpty()) {
            Spacer(modifier = Modifier.height(18.dp))
            ExchangeConversionCardComponent(exchanges = variants.conversions)
        }
    }
}

@Composable
private fun ExchangeHeaderComponent() {
    Row(
        modifier = Modifier
            .padding(
                start = 2.dp,
                end = 2.dp,
            )
            .fillMaxWidth()
    ) {

        Text(
            text = stringResource(id = R.string.exchanges_rates_exchange_money),
            style = MaterialTheme.typography.h3,
            color = ApplicationTheme.colors.secondaryVariant,
            modifier = Modifier
                .weight(2f)

        )

        Text(
            text = stringResource(id = R.string.exchanges_rates_exchange_buy),
            style = MaterialTheme.typography.h3,
            color = ApplicationTheme.colors.secondaryVariant,
            modifier = Modifier
                .weight(1f)
        )

        Text(
            text = stringResource(id = R.string.exchanges_rates_exchange_sell),
            style = MaterialTheme.typography.h3,
            color = ApplicationTheme.colors.secondaryVariant,
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
private fun ExchangeVariantsCardComponent(
    exchanges: List<ExchangesVariant>
) {
    Card(
        elevation = 4.dp,
        backgroundColor = ApplicationTheme.colors.background,
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                )
        ) {
            exchanges.forEachIndexed { index, variant ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,
                            bottom = 16.dp,
                        )
                ) {

                    Row(
                        modifier = Modifier
                            .weight(2.5f)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_usd),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(20.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = variant.name,
                            style = MaterialTheme.typography.h2,
                            color = ApplicationTheme.colors.baseTextColor,
                            modifier = Modifier
                        )
                    }

                    Row(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = variant.buyPrice.price,
                            style = MaterialTheme.typography.h2,
                            color = ApplicationTheme.colors.baseTextColor,
                        )

                        Image(
                            painter = painterResource(id = variant.buyPrice.getIsPositiveIcon()),
                            contentDescription = null,
                            modifier = Modifier
                                .size(
                                    height = 12.dp,
                                    width = 8.dp,
                                )
                        )
                    }

                    Row(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = variant.sellPrice.price,
                            style = MaterialTheme.typography.h2,
                            color = ApplicationTheme.colors.baseTextColor,
                        )

                        Image(
                            painter = painterResource(id = variant.sellPrice.getIsPositiveIcon()),
                            contentDescription = null,
                            modifier = Modifier
                                .size(
                                    height = 12.dp,
                                    width = 8.dp,
                                )
                        )
                    }
                }

                if (index != exchanges.lastIndex) {
                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ExchangeConversionCardComponent(
    exchanges: List<ExchangesVariant>
) {
    Card(
        elevation = 4.dp,
        backgroundColor = ApplicationTheme.colors.background,
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                )
        ) {
            exchanges.forEachIndexed { index, variant ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,
                            bottom = 16.dp,
                        )
                ) {

                    Row(
                        modifier = Modifier
                            .weight(2.5f)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_usd),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(20.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = variant.name,
                            style = MaterialTheme.typography.h2,
                            color = ApplicationTheme.colors.baseTextColor,
                            modifier = Modifier
                        )
                    }

                    Row(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = variant.buyPrice.price,
                            style = MaterialTheme.typography.h2,
                            color = ApplicationTheme.colors.baseTextColor,
                        )

                        Image(
                            painter = painterResource(id = variant.buyPrice.getIsPositiveIcon()),
                            contentDescription = null,
                            modifier = Modifier
                                .size(
                                    height = 12.dp,
                                    width = 8.dp,
                                )
                        )
                    }

                    Row(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = variant.sellPrice.price,
                            style = MaterialTheme.typography.h2,
                            color = ApplicationTheme.colors.baseTextColor,
                        )

                        Image(
                            painter = painterResource(id = variant.sellPrice.getIsPositiveIcon()),
                            contentDescription = null,
                            modifier = Modifier
                                .size(
                                    height = 12.dp,
                                    width = 8.dp,
                                )
                        )
                    }
                }

                if (index != exchanges.lastIndex) {
                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ExchangeVariantComponentPreview() {
    BasicTheme {

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) {

            ExchangeVariantComponent(
                variants = ExchangesOnlineVariant(
                    exchanges = listOf(
                        ExchangesVariant(
                            name = "1 USD",
                            buyPrice = ExchangesPrice(
                                price = "3,2000",
                                isPositive = true,
                            ),
                            sellPrice = ExchangesPrice(
                                price = "3,2350",
                                isPositive = false,
                            )
                        ),
                        ExchangesVariant(
                            name = "1 EUR",
                            buyPrice = ExchangesPrice(
                                price = "3,2000",
                                isPositive = true,
                            ),
                            sellPrice = ExchangesPrice(
                                price = "3,2350",
                                isPositive = false,
                            )
                        ),
                        ExchangesVariant(
                            name = "100 RUB",
                            buyPrice = ExchangesPrice(
                                price = "3,2000",
                                isPositive = true,
                            ),
                            sellPrice = ExchangesPrice(
                                price = "3,2350",
                                isPositive = false,
                            )
                        )
                    ),
                    actualDate = "Актуальны с 11:45 12 сент. 2023",
                    conversions = listOf(
                        ExchangesVariant(
                            name = "EUR/USD",
                            buyPrice = ExchangesPrice(
                                price = "1,062",
                                isPositive = false
                            ),
                            sellPrice = ExchangesPrice(
                                price = "1,081",
                                isPositive = false,
                            )
                        ),
                        ExchangesVariant(
                            name = "EUR/RUB",
                            buyPrice = ExchangesPrice(
                                price = "1,062",
                                isPositive = false
                            ),
                            sellPrice = ExchangesPrice(
                                price = "1,081",
                                isPositive = false,
                            )
                        ),
                        ExchangesVariant(
                            name = "USD/RUB",
                            buyPrice = ExchangesPrice(
                                price = "1,062",
                                isPositive = false
                            ),
                            sellPrice = ExchangesPrice(
                                price = "1,081",
                                isPositive = false,
                            )
                        )
                    )
                ),
                modifier = Modifier
                    .padding(it)
            )

        }

    }
}