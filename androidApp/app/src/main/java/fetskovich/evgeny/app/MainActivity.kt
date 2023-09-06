package fetskovich.evgeny.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import fetskovich.evgeny.app.features.ui.addbankcard.api.AddAnotherBankCardFeatureApi
import fetskovich.evgeny.app.features.ui.authorized.bottomnav.api.AuthorizedMainScreenFeatureApi
import fetskovich.evgeny.app.features.ui.authorized.history.api.HistoryScreenApi
import fetskovich.evgeny.app.features.ui.authorized.more.api.MoreScreensGraphApi
import fetskovich.evgeny.app.features.ui.authorized.more.other.api.OtherScreenApi
import fetskovich.evgeny.app.features.ui.authorized.payments.api.PaymentsScreenApi
import fetskovich.evgeny.app.features.ui.authorized.products.api.ProductsScreenApi
import fetskovich.evgeny.app.features.ui.singlenews.api.SingleNewsFeatureApi
import fetskovich.evgeny.app.features.ui.splash.api.SplashFeatureApi
import fetskovich.evgeny.app.features.ui.unauthorized.assistance.api.AssistanceScreenApi
import fetskovich.evgeny.app.features.ui.unauthorized.bottomnav.api.UnauthorizedMainScreenFeatureApi
import fetskovich.evgeny.app.features.ui.unauthorized.cashpoints.api.CashpointsScreenApi
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.api.ExchangesScreenApi
import fetskovich.evgeny.app.features.ui.unauthorized.login.api.LoginScreenApi
import fetskovich.evgeny.app.features.ui.unauthorized.more.api.UnauthorizedMoreScreensGraphApi
import fetskovich.evgeny.app.features.ui.unauthorized.more.other.api.OtherUnauthorizedScreenApi
import fetskovich.evgeny.presentation.theme.BasicTheme

class MainActivity : ComponentActivity() {

    private val kodein by lazy {
        (application as BaseApplication).di
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BasicTheme {
                CompositionLocalProvider(
                    LocalApplicationModuleComposition provides kodein,
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        val features = remember {
                            setOf(
                                SplashFeatureApi(),
                                UnauthorizedMainScreenFeatureApi(
                                    setOf(
                                        LoginScreenApi(),
                                        CashpointsScreenApi(),
                                        ExchangesScreenApi(),
                                        AssistanceScreenApi(),
                                        UnauthorizedMoreScreensGraphApi(
                                            setOf(OtherUnauthorizedScreenApi())
                                        )
                                    ),
                                ),
                                AuthorizedMainScreenFeatureApi(
                                    setOf(
                                        ProductsScreenApi(),
                                        HistoryScreenApi(),
                                        PaymentsScreenApi(),
                                        MoreScreensGraphApi(
                                            setOf(OtherScreenApi())
                                        )
                                    )
                                ),
                                AddAnotherBankCardFeatureApi(),
                                SingleNewsFeatureApi(),
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
}


