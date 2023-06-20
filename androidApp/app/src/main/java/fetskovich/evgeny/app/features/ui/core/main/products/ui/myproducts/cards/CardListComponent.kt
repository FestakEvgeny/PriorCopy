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
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fetskovich.evgeny.presentation.theme.BasicTheme
import fetskovich.evgeny.recipeskmm.app.R

@Composable
fun CardListComponent(
    items: List<CardListItem>,
    modifier: Modifier
) {
    Card(
        modifier = modifier
    ) {

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
        modifier = modifier
            .fillMaxWidth()
            .padding(
                all = 10.dp
            ),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Icon(
                    painter = painterResource(id = item.cardIcon),
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
                            style = MaterialTheme.typography.subtitle2
                        )

                        Text(
                            text = item.cardBalanceMainCurrency,
                            style = MaterialTheme.typography.subtitle2
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
                            style = MaterialTheme.typography.body2
                        )

                        Text(
                            text = item.cardBalanceOffCurrency,
                            style = MaterialTheme.typography.body2
                        )
                    }

                }

            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .padding(
                        top = 10.dp,
                    )
                    .clickable(
                        onClick = onSortChange
                    )
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp)
                )

                Spacer(
                    modifier = Modifier
                        .width(6.dp)
                )

                Text(
                    text = stringResource(id = R.string.products_screen_card_item_sort),
                )
            }
        }
    }
}

@Preview
@Composable
private fun CardComponentPreview() {
    BasicTheme {
        CardComponent(
            item = CardListItem(
                id = "test",
                cardIcon = R.drawable.ic_profile,
                cardName = "DK9581",
                cardNumber = "---- 9581",
                cardBalanceMainCurrency = "4 959,03 BYN",
                cardBalanceOffCurrency = "1 654,11 USD"
            ),
            onSortChange = {}
        )
    }
}