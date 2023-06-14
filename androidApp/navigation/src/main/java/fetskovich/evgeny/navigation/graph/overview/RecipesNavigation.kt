package fetskovich.evgeny.navigation.graph.overview

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import fetskovich.evgeny.navigation.NavHostNavigationCommand

object RecipesNavigation {

    private const val CATEGORY_ID_KEY = "categoryId"

    const val route = CategoriesNavigation.route +
            "/recipes/" +
            "{$CATEGORY_ID_KEY}"

    val args = listOf(
        navArgument(CATEGORY_ID_KEY) {
            type = NavType.IntType
        }
    )

    fun getCategoryId(entry: NavBackStackEntry) =
        requireNotNull(entry.arguments).getInt(CATEGORY_ID_KEY)

    fun command(categoryId: Int) = object : NavHostNavigationCommand {
        override val destination: String = "${CategoriesNavigation.route}/recipes/$categoryId"
        override val arguments: List<NamedNavArgument> = args
    }
}