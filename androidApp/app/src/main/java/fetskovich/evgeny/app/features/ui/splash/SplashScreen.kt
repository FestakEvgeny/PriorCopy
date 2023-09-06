package fetskovich.evgeny.app.features.ui.splash

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fetskovich.evgeny.app.features.ui.splash.api.SplashScreenNavigation
import fetskovich.evgeny.app.features.ui.unauthorized.bottomnav.api.UnauthorizedMainScreenNavigation
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.recipeskmm.app.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

private const val ANIMATION_DURATION = 1000L
private const val UPDATE_OFFSET_EMISSIONS_COUNT = 20

@Composable
fun SplashScreen(
    navController: NavController?,
) {
    val scope = rememberCoroutineScope()

    Screen()

    LaunchedEffect(Unit) {
        scope.launch {
            delay(ANIMATION_DURATION)
            navController?.navigate(UnauthorizedMainScreenNavigation.route) {
                popUpTo(SplashScreenNavigation.route) {
                    inclusive = true
                }
            }
        }
    }
}

@Composable
private fun Screen() {
    val offset = remember { mutableIntStateOf(0) }
    val animatedOffset by animateIntAsState(
        targetValue = offset.intValue,
        label = "title x offset"
    )
    val offsetDpValue = with(LocalDensity.current) { animatedOffset.toDp() }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.splash_bank_copy),
            style = MaterialTheme.typography.subtitle2,
            color = ApplicationTheme.colors.primaryVariant,
            modifier = Modifier
                .offset(
                    x = offsetDpValue
                )
        )
    }

    TitleOffsetAnimation(
        onUpdateValue = { additionalOffset ->
            offset.intValue = offset.intValue + additionalOffset
        }
    )
}

@Composable
private fun TitleOffsetAnimation(
    onUpdateValue: (Int) -> Unit,
) {
    val halfScreenWidth = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp.dp.toPx()
    }

    LaunchedEffect(Unit) {
        // Just testing some custom written stuff
        flow {
            var emittedCount = 0
            while (emittedCount < UPDATE_OFFSET_EMISSIONS_COUNT) {
                emittedCount += 1
                emit((halfScreenWidth / UPDATE_OFFSET_EMISSIONS_COUNT).toInt())
                delay(ANIMATION_DURATION / UPDATE_OFFSET_EMISSIONS_COUNT)
            }
        }.collectLatest {
            onUpdateValue(it)
        }
    }
}