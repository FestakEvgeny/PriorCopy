package fetskovich.evgeny.app.features.ui.core.main.products.ui.toolbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fetskovich.evgeny.presentation.theme.BasicTheme
import fetskovich.evgeny.recipeskmm.app.R

@Composable
fun ProductsToolbar(
    modifier: Modifier,
    onProfileClick: () -> Unit,
    onSupportClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Filled.Home,
            contentDescription = null,
            modifier = Modifier
                .padding(
                    start = 16.dp
                )
                .size(32.dp)

        )

        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_message),
                contentDescription = null,
                modifier = Modifier
                    .clickable(
                        onClick = onSupportClick
                    )
                    .padding(
                        end = 16.dp
                    )
                    .size(32.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = null,
                modifier = Modifier
                    .clickable(
                        onClick = onProfileClick
                    )
                    .padding(
                        end = 16.dp
                    )
                    .size(32.dp)
            )
        }
    }
}

@Preview
@Composable
private fun ProductsToolbarPreview() {
    BasicTheme {
        ProductsToolbar(
            modifier = Modifier,
            onProfileClick = {},
            onSupportClick = {},
        )
    }
}