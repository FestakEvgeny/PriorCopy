package fetskovich.evgeny.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fetskovich.evgeny.app.features.ui.main.bottomnav.MainBottomNavigationFeatureApi
import fetskovich.evgeny.app.features.ui.main.overview.OverviewFeatureApi
import fetskovich.evgeny.app.features.ui.main.overview.category.CategoryFeatureApi
import fetskovich.evgeny.app.features.ui.main.overview.recipes.RecipesFeatureApi
import fetskovich.evgeny.app.features.ui.main.settings.SettingsFeatureApi
import fetskovich.evgeny.app.features.ui.main.settings.general.GeneralSettingsFeatureApi
import fetskovich.evgeny.app.features.ui.splash.SplashFeatureApi
import fetskovich.evgeny.architecture.Greeting
import fetskovich.evgeny.recipeskmm.android.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val features = remember {
                        setOf(
                            SplashFeatureApi(),
                            MainBottomNavigationFeatureApi(
                                setOf(
                                    OverviewFeatureApi(
                                        setOf(RecipesFeatureApi(), CategoryFeatureApi())
                                    ),
                                    SettingsFeatureApi(
                                        setOf(
                                            GeneralSettingsFeatureApi(),
                                        )
                                    )
                                )
                            ),
                        )
                    }

                    ApplicationNavHost(
                        features = features,
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
