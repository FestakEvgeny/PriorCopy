package fetskovich.evgeny.navigation

import androidx.navigation.NamedNavArgument

class BackNavHostCommand : NavHostNavigationCommand {

    override val destination: String = "back"
    override val arguments: List<NamedNavArgument> = listOf()
}