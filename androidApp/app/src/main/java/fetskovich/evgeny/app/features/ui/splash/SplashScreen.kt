package fetskovich.evgeny.app.features.ui.splash

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import fetskovich.evgeny.app.features.ui.core.bottomnav.api.BottomScreenNavigation
import fetskovich.evgeny.app.features.ui.splash.api.SplashScreenNavigation
import fetskovich.evgeny.navigation.graph.overview.OverviewRootNavGraph
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    navController: NavController?,
) {
    val scope = rememberCoroutineScope()

    Text(text = "SPlash")
    LaunchedEffect(Unit) {
        scope.launch {
            delay(600L)
            navController?.navigate(BottomScreenNavigation.route) {
                popUpTo(SplashScreenNavigation.route) {
                    inclusive = true
                }
            }
        }
    }
}