package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.profile

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.profile.patient.PatientProfileScreen

@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {
    if (viewModel.state.isPatientAccount)
        PatientProfileScreen(navController = navController)

}