package yunuiy_hacker.ryzhaya_tetenka.nebolit

import android.os.Bundle
import android.provider.Settings.Global
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SaveReadPersonDataUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph.NavGraph
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.NebolitTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var saveReadPersonDataUseCase: SaveReadPersonDataUseCase

    @Inject
    lateinit var dataStoreHelper: DataStoreHelper

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen()
        setContent {
            val navController: NavHostController = rememberAnimatedNavController()

            val theme = dataStoreHelper.getTheme().collectAsState(initial = false)

            val user = saveReadPersonDataUseCase.readUser.invoke()
            NebolitTheme(darkTheme = theme.value) {
                NavGraph(
                    navController = navController,
                    startDestination = if (user == null) if (dataStoreHelper.getAppEntry()
                            .collectAsState(initial = false).value
                    ) Route.SignInScreen.route else Route.OnboardingScreen.route else if (user.passportId != 0) Route.HomeScreen.route else Route.AdminMainScreen.route
                )
            }
        }
    }
}
