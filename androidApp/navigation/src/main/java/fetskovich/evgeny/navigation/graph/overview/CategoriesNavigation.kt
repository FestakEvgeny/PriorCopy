package fetskovich.evgeny.navigation.graph.overview

import androidx.navigation.NamedNavArgument
import fetskovich.evgeny.navigation.NavHostNavigationCommand

object CategoriesNavigation {

    const val route = OverviewRootNavGraph.route +
            "/categories"

    fun command() = object : NavHostNavigationCommand {
        override val destination: String = route
        override val arguments: List<NamedNavArgument> = emptyList()
    }
}