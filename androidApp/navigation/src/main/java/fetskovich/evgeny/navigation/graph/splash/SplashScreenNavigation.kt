package fetskovich.evgeny.navigation.graph.splash

import androidx.navigation.NamedNavArgument
import fetskovich.evgeny.navigation.NavHostNavigationCommand
import fetskovich.evgeny.navigation.graph.settings.SettingsRootNavGraph

object SplashScreenNavigation {

    const val route = "/splash"

    fun command() = object : NavHostNavigationCommand {
        override val destination = route
        override val arguments: List<NamedNavArgument> = listOf()
    }
}