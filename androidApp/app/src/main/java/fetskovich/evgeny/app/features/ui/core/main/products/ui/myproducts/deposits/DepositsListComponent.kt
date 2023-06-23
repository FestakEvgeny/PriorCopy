package fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.deposits

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.recipeskmm.app.R

@Composable
fun DepositsListComponent(
    items: List<Any>,
    modifier: Modifier
) {
    if (items.isEmpty()) {
        DepositsNotFoundComponent(
            modifier = modifier,
        )
    } else {
        // dot implemenmted
    }
}

@Composable
private fun DepositsNotFoundComponent(
    modifier: Modifier
) {
    Card(
        elevation = 4.dp,
        backgroundColor = ApplicationTheme.colors.background,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 8.dp,
            ),
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = stringResource(id = R.string.prodcuts_screen_my_products_no_deposits),
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