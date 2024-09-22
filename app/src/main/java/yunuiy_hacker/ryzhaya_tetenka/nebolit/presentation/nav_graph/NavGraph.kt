package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.fill_person_data.FillPersonDataScreen
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.sign_in.SignInScreen
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.sign_up.SignUpScreen
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.home.HomeScreen
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.onboarding.OnboardingScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(navController: NavHostController, startDestination: String) {
    AnimatedNavHost(
        navController = navController, startDestination = startDestination,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(500)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(500)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            )
        }
    ) {
        composable(
            route = Route.OnboardingScreen.route
        ) {
            OnboardingScreen(navController = navController)
        }
        composable(route = Route.SignInScreen.route) {
            SignInScreen(navController = navController)
        }
        composable(route = Route.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }
        composable(route = Route.FillPersonDataScreen.route) {
            FillPersonDataScreen(navController = navController)
        }

        composable(route = Route.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
    }
}
