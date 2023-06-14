package fetskovich.evgeny.navigation.graph.settings

import androidx.navigation.NamedNavArgument
import fetskovich.evgeny.navigation.NavHostNavigationCommand

object SettingsGeneralNavigation {

    const val route = SettingsRootNavGraph.route + "/main"

    fun command() = object : NavHostNavigationCommand {
        override val destination = route
        override val arguments: List<NamedNavArgument> = listOf()
    }
}