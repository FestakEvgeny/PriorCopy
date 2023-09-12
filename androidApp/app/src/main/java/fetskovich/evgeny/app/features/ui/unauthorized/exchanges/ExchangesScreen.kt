@file:OptIn(ExperimentalFoundationApi::class)

package fetskovich.evgeny.app.features.ui.unauthorized.exchanges

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesIntent
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesScreenState
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesTabId
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.ui.ExchangeCourseVariantComponent
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.ui.ExchangeVariantComponent
import fetskovich.evgeny.presentation.theme.ApplicationTheme

@Composable
fun ExchangesScreen(
    viewModel: ExchangesViewModel,
) {

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    Screen(
        state = state,
        onTabChange = {
            viewModel.processIntent(ExchangesIntent.SelectTab(it))
        }
    )
}

@Composable
private fun Screen(
    state: ExchangesScreenState,
    onTabChange: (ExchangesTabId) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = ApplicationTheme.colors.background,
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Курсы валют")
            
            val selectedTabIndex = remember { state.tabsList.indexOf(state.selectedTabId) }
            val pagerState = rememberPagerState { state.tabsList.size }

            LaunchedEffect(selectedTabIndex) {
                pagerState.animateScrollToPage(selectedTabIndex)
            }


            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex
            ) {
                state.tabsList.forEachIndexed { index, exchangesTabId ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            onTabChange(state.tabsList[selectedTabIndex])
                        },
                        text = {
                            Text(
                                text = when(exchangesTabId) {
                                    ExchangesTabId.ONLINE -> "Онлайн"
                                    ExchangesTabId.CARD -> "По картам"
                                    ExchangesTabId.CASH -> "Наличные"
                                    ExchangesTabId.BANK -> "НБРБ"
                                }
                            )
                        },
                        selectedContentColor = ApplicationTheme.colors.primary,
                    )
                }
            }
            
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) { index ->
                when(state.tabsList[index]) {
                    ExchangesTabId.ONLINE -> ExchangeVariantComponent(testIndex = 1)
                    ExchangesTabId.CARD -> ExchangeVariantComponent(testIndex = 2)
                    ExchangesTabId.CASH -> ExchangeVariantComponent(testIndex = 3)
                    ExchangesTabId.BANK -> ExchangeCourseVariantComponent()
                }
            }
        }
    }
}