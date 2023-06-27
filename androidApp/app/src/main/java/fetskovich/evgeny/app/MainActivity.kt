package fetskovich.evgeny.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import fetskovich.evgeny.app.features.ui.addbankcard.api.AddAnotherBankCardFeatureApi
import fetskovich.evgeny.app.features.ui.core.bottomnav.api.MainBottomNavigationFeatureApi
import fetskovich.evgeny.app.features.ui.core.history.api.HistoryScreenApi
import fetskovich.evgeny.app.features.ui.core.main.api.MainScreensGraphApi
import fetskovich.evgeny.app.features.ui.core.main.login.api.LoginScreenApi
import fetskovich.evgeny.app.features.ui.core.main.products.api.ProductsScreenApi
import fetskovich.evgeny.app.features.ui.core.more.api.MoreScreensGraphApi
import fetskovich.evgeny.app.features.ui.core.more.other.api.OtherScreenApi
import fetskovich.evgeny.app.features.ui.core.payments.api.PaymentsScreenApi
import fetskovich.evgeny.app.features.ui.splash.api.SplashFeatureApi
import fetskovich.evgeny.presentation.theme.BasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val features = remember {
                        setOf(
                            SplashFeatureApi(),
                            MainBottomNavigationFeatureApi(
                                setOf(
                                    MainScreensGraphApi(
                                        setOf(
                                            LoginScreenApi(),
                                            ProductsScreenApi(),
                                        )
                                    ),
                                    HistoryScreenApi(),
                                    PaymentsScreenApi(),
                                    MoreScreensGraphApi(
                                        setOf(OtherScreenApi())
                                    )
                                )
                            ),
                            AddAnotherBankCardFeatureApi(),
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

