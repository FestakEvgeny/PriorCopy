package fetskovich.evgeny.app.features.ui

import android.util.Log
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureApi {

    fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        // Additional nav controller for the cases where we need to use the outer (like parent) nav controller
        parentNavController: NavHostController?,
        modifier: Modifier = Modifier
    )
}

fun NavGraphBuilder.register(
    feature: FeatureApi,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    feature.registerGraph(
        navGraphBuilder = this,
        navController = navController,
        parentNavController = null,
        modifier = modifier
    )
}

fun NavGraphBuilder.register(
    feature: FeatureApi,
    navController: NavHostController,
    parentNavController: NavHostController,
    modifier: Modifier = Modifier
) {
    Log.e("JEKA", "Register feature: "+feature)
    feature.registerGraph(
        navGraphBuilder = this,
        navController = navController,
        parentNavController = parentNavController,
        modifier = modifier
    )
}
