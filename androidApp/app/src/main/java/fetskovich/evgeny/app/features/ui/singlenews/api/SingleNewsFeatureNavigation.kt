package fetskovich.evgeny.app.features.ui.singlenews.api

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import fetskovich.evgeny.navigation.NavHostNavigationCommand

object SingleNewsFeatureNavigation {

    private const val NEWS_ID_KEY = "newsId"

    private const val innerRoute = "single_news_screen/"

    const val route = "$innerRoute{$NEWS_ID_KEY}"

    val args = listOf(
        navArgument(NEWS_ID_KEY) {
            type = NavType.StringType
        }
    )

    fun getNewsId(entry: NavBackStackEntry) =
        requireNotNull(entry.arguments?.getString(NEWS_ID_KEY))

    fun command(newsId: String) = object : NavHostNavigationCommand {
        override val destination: String = innerRoute + newsId
        override val arguments: List<NamedNavArgument> = args
    }
}