package fetskovich.evgeny.app.features.ui.core.main.products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductsScreenState
import fetskovich.evgeny.app.features.ui.core.main.products.ui.toolbar.ProductsToolbar

@Composable
fun ProductsScreen(
    viewModel: ProductsScreenViewModel
) {

}

@Composable
private fun Screen(
    state: ProductsScreenState,
    onProfileClick: () -> Unit,
    onSupportClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            ProductsToolbar(
                modifier = Modifier.fillMaxWidth(),
                onProfileClick = onProfileClick,
                onSupportClick = onSupportClick,
            )

            LazyColumn {
                item {

                }
            }

        }

    }
}
