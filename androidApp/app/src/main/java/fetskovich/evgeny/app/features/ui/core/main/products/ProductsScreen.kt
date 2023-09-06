package fetskovich.evgeny.app.features.ui.core.main.products

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import fetskovich.evgeny.app.features.ui.addbankcard.api.AddAnotherBankCardNavigation
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.BankCardVariant
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.MyProductsState
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductType
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductsBottomSheetState
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductsScreenIntent
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductsScreenState
import fetskovich.evgeny.app.features.ui.core.main.products.ui.exchange.ExchangeRateComponent
import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.MyProductsComponent
import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.addproduct.AddMyProductBottomSheetComponent
import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.cards.CardListItem
import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.types.ProductTypeListItem
import fetskovich.evgeny.app.features.ui.core.main.products.ui.news.NewsComponent
import fetskovich.evgeny.app.features.ui.core.main.products.ui.toolbar.ProductsToolbar
import fetskovich.evgeny.app.features.ui.singlenews.api.SingleNewsFeatureNavigation
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme
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

    BottomSheetScreen(
        state = state,
        onProfileClick = { /*TODO*/ },
        onSupportClick = { /*TODO*/ },
        onAddProductClick = {
            viewModel.processIntent(ProductsScreenIntent.ShowAddProductDialog)
        },
        onSortChanged = { /*TODO*/ },
        onChangeProductType = { product ->
            viewModel.processIntent(
                ProductsScreenIntent.ChangeProductType(product.id)
            )
        },
        createCardClick = {
            viewModel.processIntent(ProductsScreenIntent.DismissAddProductDialog)
        },
        createCardOfAnotherBankClick = {
            viewModel.processIntent(ProductsScreenIntent.DismissAddProductDialog)
            parentNavController?.navigate(AddAnotherBankCardNavigation.route)
        },
        openOnlineDeposit = {
            viewModel.processIntent(ProductsScreenIntent.DismissAddProductDialog)
        },
        requestForOpenCredit = {
            viewModel.processIntent(ProductsScreenIntent.DismissAddProductDialog)
        },
        onNewsClick = { id ->
            parentNavController?.navigate(
                route = SingleNewsFeatureNavigation.command(id).destination,
            )
        },
        onShowFullNewsClick = { /*TODO*/ },
        executeIntent = viewModel::processIntent,
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BottomSheetScreen(
    state: ProductsScreenState,
    onProfileClick: () -> Unit,
    onSupportClick: () -> Unit,
    onAddProductClick: () -> Unit,
    onSortChanged: () -> Unit,
    createCardClick: () -> Unit,
    createCardOfAnotherBankClick: () -> Unit,
    openOnlineDeposit: () -> Unit,
    requestForOpenCredit: () -> Unit,
    onNewsClick: (id: String) -> Unit,
    onShowFullNewsClick: () -> Unit,
    onChangeProductType: (ProductTypeListItem) -> Unit,
    executeIntent: (ProductsScreenIntent) -> Unit,
) {
    println("JEKA Received bottom sheet state $state")
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = false,
        confirmValueChange = {
            if (it == ModalBottomSheetValue.Hidden) {
                state.bottomSheetState?.let(::getHideBottomSheetIntent)?.let(executeIntent)
            }
            true
        },
        animationSpec = tween(durationMillis = 300, easing = LinearEasing),
    )

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetBackgroundColor = ApplicationTheme.colors.background,
        sheetShape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp,
        ),
        sheetContent = {
            BottomSheetContent(
                state = state.bottomSheetState,
                createCardClick = createCardClick,
                createCardOfAnotherBankClick = createCardOfAnotherBankClick,
                openOnlineDeposit = openOnlineDeposit,
                requestForOpenCredit = requestForOpenCredit,
            )
        },
        modifier = Modifier
            .fillMaxSize()
    ) {
        Screen(
            state = state,
            onAddProductClick = onAddProductClick,
            onSortChanged = onSortChanged,
            onSupportClick = onSupportClick,
            onProfileClick = onProfileClick,
            onChangeProductType = onChangeProductType,
            onNewsClick = onNewsClick,
            onShowFullNewsClick = onShowFullNewsClick,
        )
    }

    LaunchedEffect(key1 = state.bottomSheetState) {
        if (state.bottomSheetState == null) {
            sheetState.hide()
        } else {
            sheetState.show()
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
    onNewsClick: (id: String) -> Unit,
    onShowFullNewsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            ProductsToolbar(
                modifier = Modifier
                    .fillMaxWidth(),
                onProfileClick = onProfileClick,
                onSupportClick = onSupportClick,
            )

            LazyColumn {

                item {
                    NewsComponent(
                        news = state.news,
                        onNewsClick = onNewsClick,
                        onShowFullNewsClick = onShowFullNewsClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                            )
                    )
                }

                item {
                    MyProductsComponent(
                        state = state.productsSectionState,
                        onAddProductClick = onAddProductClick,
                        onSortChanged = onSortChanged,
                        onChangeProductType = onChangeProductType,
                    )
                }

                item {
                    ExchangeRateComponent(
                        exchangeRate = state.exchangeRateState,
                        totalSum = state.totalCardsSum,
                        modifier = Modifier,
                    )
                }
            }
        }
    }
}

private fun getHideBottomSheetIntent(
    currentState: ProductsBottomSheetState,
): ProductsScreenIntent {
    return when (currentState) {
        ProductsBottomSheetState.AddProduct -> ProductsScreenIntent.DismissAddProductDialog
    }
}

@Composable
private fun ColumnScope.BottomSheetContent(
    state: ProductsBottomSheetState?,
    createCardClick: () -> Unit,
    createCardOfAnotherBankClick: () -> Unit,
    openOnlineDeposit: () -> Unit,
    requestForOpenCredit: () -> Unit,
) {
    when (state) {
        ProductsBottomSheetState.AddProduct -> AddMyProductBottomSheetComponent(
            createCardClick = createCardClick,
            createCardOfAnotherBankClick = createCardOfAnotherBankClick,
            openOnlineDeposit = openOnlineDeposit,
            requestForOpenCredit = requestForOpenCredit
        )

        else -> {
            // do nothing
        }
    }
}

@Preview
@Composable
private fun BottomSheetScreenPreview() {
    BasicTheme {
        BottomSheetScreen(
            state = ProductsScreenState(
                userEmail = "",
                productsSectionState = MyProductsState(
                    selectedType = ProductType.CREDITS,
                    availableTypes = listOf(
                        ProductTypeListItem(
                            id = ProductType.CARDS,
                            text = "Cards",
                            isSelected = false,
                        ),
                        ProductTypeListItem(
                            id = ProductType.CREDITS,
                            text = "Credits",
                            isSelected = true,
                        ),
                        ProductTypeListItem(
                            id = ProductType.DEPOSITS,
                            text = "Deposits",
                            isSelected = false,
                        ),
                    ),
                    cardsList = listOf(
                        CardListItem(
                            id = "1",
                            cardIcon = BankCardVariant.VISA,
                            cardName = "9541B",
                            cardNumber = "${CardListItem.CARD_NUMBER_SEPARATOR} 9581",
                            cardBalanceMainCurrency = "4,315.12 BYR",
                            cardBalanceOffCurrency = "4,213.00 USD",
                        ),
                        CardListItem(
                            id = "2",
                            cardIcon = BankCardVariant.VISA,
                            cardName = "9541B",
                            cardNumber = "${CardListItem.CARD_NUMBER_SEPARATOR} 9581",
                            cardBalanceMainCurrency = "4,315.12 BYR",
                            cardBalanceOffCurrency = "4,213.00 USD",
                        ),
                    ),
                    creditsList = emptyList(),
                    depositsList = emptyList(),
                ),
                totalCardsSum = "3,2050"
            ),
            onProfileClick = { },
            onSupportClick = { },
            onAddProductClick = { },
            onSortChanged = { },
            onChangeProductType = {},
            createCardClick = {},
            createCardOfAnotherBankClick = {},
            openOnlineDeposit = {},
            requestForOpenCredit = {},
            onNewsClick = {},
            onShowFullNewsClick = {},
            executeIntent = {},
        )
    }
}