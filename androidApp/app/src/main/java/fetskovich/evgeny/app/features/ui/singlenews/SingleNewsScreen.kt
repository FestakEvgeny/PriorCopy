package fetskovich.evgeny.app.features.ui.singlenews

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import fetskovich.evgeny.app.features.ui.singlenews.mvi.SingleNewsScreenState
import fetskovich.evgeny.app.features.ui.singlenews.ui.toolbar.SINGLE_NEWS_COLLAPSED_TOOLBAR_HEIGHT
import fetskovich.evgeny.app.features.ui.singlenews.ui.toolbar.SINGLE_NEWS_EXPANDED_TOOLBAR_HEIGHT
import fetskovich.evgeny.app.features.ui.singlenews.ui.toolbar.SingleNewsCollapsedToolbar
import fetskovich.evgeny.app.features.ui.singlenews.ui.toolbar.SingleNewsExpandedToolbar
import fetskovich.evgeny.components.text.HtmlTextField

@Composable
fun SingleNewsScreen(
    navController: NavController,
    viewModel: SingleNewsViewModel,
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    Screen(
        state = state,
    )
}

@Composable
private fun Screen(state: SingleNewsScreenState) {
    val columnState = rememberLazyListState()

    val overlapHeightPx = with(LocalDensity.current) {
        SINGLE_NEWS_EXPANDED_TOOLBAR_HEIGHT.toPx() - SINGLE_NEWS_COLLAPSED_TOOLBAR_HEIGHT.toPx()
    }

    // lerp

    val isCollapsed: Boolean by remember {
        derivedStateOf {
            val isFirstItemHidden =
                columnState.firstVisibleItemScrollOffset > overlapHeightPx
            isFirstItemHidden || columnState.firstVisibleItemIndex > 0
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box {
            SingleNewsCollapsedToolbar(
                title = state.title,
                isCollapsed = isCollapsed,
                modifier = Modifier
                    .zIndex(2f)
            )
            LazyColumn(
                state = columnState,
                modifier = Modifier
                    .padding(it)
            ) {

                item {
                    SingleNewsExpandedToolbar(title = state.title)
                }

                item {
                    HtmlTextField(
                        text = state.htmlDescription,
                        modifier = Modifier
                            .padding(
                                start = 16.dp,
                                top = 16.dp,
                                end = 16.dp,
                            )
                    )
                }

                item {
                    Spacer(
                        modifier = Modifier
                            .height(60.dp)
                    )
                }
            }
        }
    }
}
