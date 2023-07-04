package fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.BankCardVariant
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme
import fetskovich.evgeny.recipeskmm.app.R

@Composable
fun CardListComponent(
    items: List<CardListItem>,
    onSortChange: () -> Unit,
    modifier: Modifier
) {

    if (items.isEmpty()) {
        CardNotFoundComponent(
            modifier = modifier,
        )
    } else {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(
                    bottom = 8.dp
                )
        ) {
            items(
                items = items,
                key = { item ->
                    item.id
                }
            ) { item ->
                CardComponent(
                    item = item,
                    onSortChange = onSortChange,
                    modifier = modifier
                        .padding(
                            end = 12.dp,
                        )
                )
            }
        }
    }
}

@Composable
private fun CardNotFoundComponent(
    modifier: Modifier
) {
    Card(
        elevation = 6.dp,
        backgroundColor = ApplicationTheme.colors.background,
        modifier = modifier
            .padding(
                bottom = 8.dp,
            ),
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = stringResource(id = R.string.products_screen_my_products_no_cards),
                style = MaterialTheme.typography.caption,
                color = ApplicationTheme.colors.primaryVariant,
            )

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .padding(
                        top = 25.dp,
                    )
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.products_screen_my_products_create_now),
                    style = MaterialTheme.typography.body2.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    color = ApplicationTheme.colors.activeLink,
                )
            }
        }
    }
}

@Composable
private fun CardComponent(
    item: CardListItem,
    onSortChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = 6.dp,
        backgroundColor = ApplicationTheme.colors.background,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier
                    .padding(
                        top = 4.dp,
                    )
                    .fillMaxWidth()
            ) {

                Icon(
                    painter = painterResource(id = item.cardIcon.imageId),
                    contentDescription = null,
                    modifier = Modifier
                        .width(30.dp)
                        .height(20.dp)
                )

                Column {

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = item.cardName,
                            style = MaterialTheme.typography.subtitle2.copy(
                                fontSize = 14.sp,
                            ),
                            color = ApplicationTheme.colors.primaryVariant,
                        )

                        Text(
                            text = item.cardBalanceMainCurrency,
                            style = MaterialTheme.typography.subtitle2.copy(
                                fontSize = 14.sp,
                            ),
                            color = ApplicationTheme.colors.primaryVariant,
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 4.dp
                            )
                    ) {
                        Text(
                            text = item.cardNumber,
                            style = MaterialTheme.typography.body2.copy(
                                color = MaterialTheme.colors.primaryVariant.copy(
                                    alpha = 0.4f
                                ),
                                fontSize = 12.5.sp,
                            )
                        )

                        Text(
                            text = item.cardBalanceOffCurrency,
                            style = MaterialTheme.typography.body2.copy(
                                color = MaterialTheme.colors.primaryVariant.copy(
                                    alpha = 0.4f
                                ),
                                fontSize = 12.5.sp,
                            ),
                        )
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .padding(
                        top = 20.dp,
                    )
                    .clickable(
                        onClick = onSortChange
                    )
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_more),
                    tint = ApplicationTheme.colors.activeLink,
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp)
                )

                Spacer(
                    modifier = Modifier
                        .width(6.dp)
                )

                Text(
                    text = stringResource(id = R.string.products_screen_my_products_card_item_sort),
                    color = ApplicationTheme.colors.activeLink,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.5.sp,
                )
            }
        }
    }
}

@Preview
@Composable
private fun CardComponentsListPreview() {
    BasicTheme {
        CardListComponent(
            items = listOf(
                CardListItem(
                    id = "test",
                    cardIcon = BankCardVariant.VISA,
                    cardName = "DK9581",
                    cardNumber = "${CardListItem.CARD_NUMBER_SEPARATOR} 9581",
                    cardBalanceMainCurrency = "4 959,03 BYN",
                    cardBalanceOffCurrency = "1 654,11 USD"
                ),
                CardListItem(
                    id = "test2",
                    cardIcon = BankCardVariant.VISA,
                    cardName = "DK9581",
                    cardNumber = "${CardListItem.CARD_NUMBER_SEPARATOR} 9581",
                    cardBalanceMainCurrency = "4 959,03 BYN",
                    cardBalanceOffCurrency = "1 654,11 USD"
                ),
                CardListItem(
                    id = "tes3",
                    cardIcon = BankCardVariant.VISA,
                    cardName = "DK9581",
                    cardNumber = "${CardListItem.CARD_NUMBER_SEPARATOR} 9581",
                    cardBalanceMainCurrency = "4 959,03 BYN",
                    cardBalanceOffCurrency = "1 654,11 USD"
                )
            ),
            onSortChange = {},
            modifier = Modifier
        )
    }
}

@Preview
@Composable
private fun CardComponentPreview() {
    BasicTheme {
        CardComponent(
            item = CardListItem(
                id = "test",
                cardIcon = BankCardVariant.VISA,
                cardName = "DK9581",
                cardNumber = "${CardListItem.CARD_NUMBER_SEPARATOR} 9581",
                cardBalanceMainCurrency = "4 959,03 BYN",
                cardBalanceOffCurrency = "1 654,11 USD"
            ),
            onSortChange = {}
        )
    }
}

@Preview
@Composable
private fun CardNotFoundComponentPreview() {
    BasicTheme {
        CardNotFoundComponent(
            modifier = Modifier
        )

    }
}