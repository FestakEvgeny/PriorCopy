package fetskovich.evgeny.app.features.ui.core.main.products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import fetskovich.evgeny.app.features.ui.core.main.login.api.LoginScreenNavigation
import fetskovich.evgeny.app.features.ui.core.main.login.mvi.LoginScreenAction
import fetskovich.evgeny.app.features.ui.core.main.products.api.ProductsScreenNavigation
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductsScreenIntent
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductsScreenState
import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.MyProductsComponent
import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.types.ProductTypeListItem
import fetskovich.evgeny.app.features.ui.core.main.products.ui.toolbar.ProductsToolbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun ProductsScreen(
    viewModel: ProductsScreenViewModel,
    parentNavController: NavHostController?,
    navController: NavHostController,
) {

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    Screen(
        state = state,
        onProfileClick = { /*TODO*/ },
        onSupportClick = { /*TODO*/ },
        onAddProductClick = { /*TODO*/ },
        onSortChanged = { /*TODO*/ },
        onChangeProductType = { product ->
            viewModel.processIntent(
                ProductsScreenIntent.ChangeProductType(product.id)
            )
        }
    )

    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycleScope.launch {
            // TODO flowWithLifecycle
            viewModel.actionFlow.collectLatest { action ->
                when (action) {

                }
            }
        }
    }
}

@Composable
private fun Screen(
    state: ProductsScreenState,
    onProfileClick: () -> Unit,
    onSupportClick: () -> Unit,
    onAddProductClick: () -> Unit,
    onSortChanged: () -> Unit,
    onChangeProductType: (ProductTypeListItem) -> Unit,
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
                    MyProductsComponent(
                        state = state.productsSectionState,
                        onAddProductClick = onAddProductClick,
                        onSortChanged = onSortChanged,
                        onChangeProductType = onChangeProductType,
                    )
                }
            }

        }

    }
}
