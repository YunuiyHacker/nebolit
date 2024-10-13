package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.fill_person_data.FillPersonDataScreen
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.fill_person_data.FillPersonDataViewModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.sign_in.SignInScreen
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.sign_up.SignUpScreen
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.edit_person_data.EditPersonDataScreen
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.home.HomeScreen
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.profile.ProfileScreen
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.onboarding.OnboardingScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(navController: NavHostController, startDestination: String) {

    AnimatedNavHost(navController = navController,
        startDestination = startDestination,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(500)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(500)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(500)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(500)
            )
        }) {
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
        composable(
            route = Route.FillPersonDataScreen.route + "/{user_id}",
            arguments = listOf(navArgument("user_id") {
                type = NavType.IntType
                nullable = false
            })
        ) { entry ->
            val viewModel: FillPersonDataViewModel = hiltViewModel()
            viewModel.state.userId = entry.arguments?.getInt("user_id")!!
            FillPersonDataScreen(
                navController = navController
            )
        }

        composable(route = Route.HomeScreen.route,
            enterTransition = { EnterTransition.None },
            popEnterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popExitTransition = { ExitTransition.None }) {
            HomeScreen(navController = navController)
        }

        composable(route = Route.ProfileScreen.route,
            enterTransition = { EnterTransition.None },
            popEnterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popExitTransition = { ExitTransition.None }) {
            ProfileScreen(navController = navController)
        }

        composable(route = Route.EditPersonDataScreen.route) {
            EditPersonDataScreen(navController = navController)
        }
    }
}
