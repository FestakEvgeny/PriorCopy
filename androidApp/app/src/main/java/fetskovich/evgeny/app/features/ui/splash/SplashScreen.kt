package fetskovich.evgeny.app.features.ui.splash

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import fetskovich.evgeny.navigation.graph.overview.OverviewRootNavGraph
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    navController: NavController,
) {
    val scope = rememberCoroutineScope()

    Text(text = "SPlash")
    LaunchedEffect(Unit) {
           scope.launch {
               delay(600L)
               navController.navigate(OverviewRootNavGraph.route)
           }
    }
}