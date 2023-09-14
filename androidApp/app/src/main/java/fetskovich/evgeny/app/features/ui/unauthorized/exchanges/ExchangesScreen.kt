@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package fetskovich.evgeny.app.features.ui.unauthorized.exchanges

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesIntent
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesScreenState
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesTabId
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.ui.ExchangeCourseVariantComponent
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.ui.ExchangeVariantComponent
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme

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

            Spacer(modifier = Modifier.height(26.dp))

            Text(
                text = "Курсы валют",
                style = MaterialTheme.typography.subtitle1.copy(
                    fontSize = 22.sp,
                ),
                color = ApplicationTheme.colors.baseTextColor,
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                    )
            )

            Spacer(modifier = Modifier.height(20.dp))

            var selectedTabIndex by remember {
                mutableIntStateOf(state.tabsList.indexOf(state.selectedTabId))
            }

            val pagerState = rememberPagerState { state.tabsList.size }

            LaunchedEffect(selectedTabIndex) {
                pagerState.animateScrollToPage(selectedTabIndex)
            }

            LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
                if (!pagerState.isScrollInProgress) {
                    selectedTabIndex = pagerState.currentPage
                }
            }

            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                edgePadding = 0.dp,
                backgroundColor = ApplicationTheme.colors.background,
                contentColor = ApplicationTheme.colors.primaryVariant,
                divider = {
                    Divider(
                        thickness = 1.dp,
                        modifier = Modifier
                            .shadow(
                                elevation = 1.dp,
                            )
                    )
                },
                indicator = { tabPositions ->
                    // TODO Implement custom indicator modifier with offset that depends on the pager state
                    TabRowDefaults.Indicator(
                        height = 4.dp,
                        color = ApplicationTheme.colors.primary,
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedTabIndex])
                            .clip(
                                RoundedCornerShape(
                                    topStart = 30.dp,
                                    topEnd = 30.dp,
                                )
                            )
                    )
                }
            ) {
                state.tabsList.forEachIndexed { index, exchangesTabId ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index
                            onTabChange(state.tabsList[index])
                        },
                        text = {
                            Text(
                                text = when (exchangesTabId) {
                                    ExchangesTabId.ONLINE -> "Онлайн"
                                    ExchangesTabId.CARD -> "По картам"
                                    ExchangesTabId.CASH -> "Наличные"
                                    ExchangesTabId.BANK -> "НБРБ"
                                },
                                style = MaterialTheme.typography.subtitle2,
                            )
                        },
                        selectedContentColor = ApplicationTheme.colors.baseTextColor,
                        unselectedContentColor = ApplicationTheme.colors.secondaryVariant
                    )
                }
            }
            Divider(
                thickness = 1.dp,
                modifier = Modifier
                    .shadow(
                        elevation = 1.dp,
                    )
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) { index ->
                when (state.tabsList[index]) {
                    ExchangesTabId.ONLINE -> ExchangeVariantComponent(testIndex = 1)
                    ExchangesTabId.CARD -> ExchangeVariantComponent(testIndex = 2)
                    ExchangesTabId.CASH -> ExchangeVariantComponent(testIndex = 3)
                    ExchangesTabId.BANK -> ExchangeCourseVariantComponent()
                }
            }
        }
    }
}

@Preview
@Composable
private fun ScreenPreview() {
    BasicTheme {
        Screen(
            state = ExchangesScreenState(
                tabsList = listOf(
                    ExchangesTabId.ONLINE,
                    ExchangesTabId.CARD,
                    ExchangesTabId.CASH,
                    ExchangesTabId.BANK,
                ),
                selectedTabId = ExchangesTabId.ONLINE,
            ),
            onTabChange = {}
        )
    }
}