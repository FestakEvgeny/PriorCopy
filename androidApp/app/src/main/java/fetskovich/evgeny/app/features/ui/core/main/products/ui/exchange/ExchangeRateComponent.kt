package fetskovich.evgeny.app.features.ui.core.main.products.ui.exchange

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme
import fetskovich.evgeny.recipeskmm.app.R

@Composable
fun ExchangeRateComponent(
    firstRate: ExchangeRateUiItem,
    modifier: Modifier,
) {
    Row(modifier = modifier) {

        ExchangeRateElement(
            item = firstRate,
            modifier = Modifier
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
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column {
            Row {

                Icon(
                    painter = painterResource(id = R.drawable.ic_photo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                )

                Column {
                    Text(
                        text = "Онлайн",
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 12.sp,
                        ),
                        color = ApplicationTheme.colors.secondaryVariant,
                    )
                    Text(
                        text = "1 USD",
                        style = MaterialTheme.typography.subtitle2,
                        color = ApplicationTheme.colors.primaryVariant,
                    )
                }

            }

            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(
                    text = "3,2050",
                    style = MaterialTheme.typography.subtitle2,
                    color = ApplicationTheme.colors.primaryVariant,
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_photo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(10.dp)
                )
            }

            Text(
                text = "Покупка",
                style = MaterialTheme.typography.body2.copy(
                    fontSize = 12.sp,
                ),
                color = ApplicationTheme.colors.secondaryVariant,
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