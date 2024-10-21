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
import com.google.gson.Gson
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.AdminPatient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.main.AdminMainScreen
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.patients.patients_add.AdminPatientsAddScreen
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.patients.patients_edit.AdminPatientEditScreen
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.patients.patients_edit.AdminPatientsEditViewModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.patients.patients_list.AdminPatientsListScreen
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
    val gson: Gson = Gson()

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

        //admin
        composable(route = Route.AdminMainScreen.route) {
            AdminMainScreen(navController = navController)
        }

        composable(route = Route.AdminPatientsListScreen.route) {
            AdminPatientsListScreen(navController = navController)
        }

        composable(
            route = Route.AdminPatientsEditScreen.route + "/{admin_patient}",
            arguments = listOf(navArgument("admin_patient") {
                type = NavType.StringType
                nullable = false
            })
        ) { entry ->
            val viewModel: AdminPatientsEditViewModel = hiltViewModel()
            val adminPatient = gson.fromJson(
                entry.arguments?.getString("admin_patient")!!,
                AdminPatient::class.java
            )
            viewModel.state.adminPatient =
                adminPatient
            AdminPatientEditScreen(
                navController = navController,
                viewModel = viewModel,
                adminPatient
            )
        }

        composable(route = Route.AdminPatientsAddScreen.route) {
            AdminPatientsAddScreen(navController = navController)
        }

        composable(route = Route.AdminDoctorsListScreen.route) {

        }
    }
}
