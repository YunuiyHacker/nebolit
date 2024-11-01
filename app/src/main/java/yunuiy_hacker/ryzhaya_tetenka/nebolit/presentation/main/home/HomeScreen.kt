package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.home.doctor.DoctorHomeScreen
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.home.patient.PatientHomeScreen

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    if (viewModel.state.isPatientAccount)
        PatientHomeScreen(navController = navController)
    else {
        DoctorHomeScreen(navController = navController)
    }
}