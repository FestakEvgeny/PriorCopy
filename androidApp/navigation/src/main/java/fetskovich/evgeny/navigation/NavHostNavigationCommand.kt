package fetskovich.evgeny.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import fetskovich.evgeny.architecture.NavigationCommand

interface NavHostNavigationCommand : NavigationCommand {

    val destination: String

    val arguments: List<NamedNavArgument>

    fun navOptions(): NavOptions? = null
    fun navExtras(): Navigator.Extras? = null
}