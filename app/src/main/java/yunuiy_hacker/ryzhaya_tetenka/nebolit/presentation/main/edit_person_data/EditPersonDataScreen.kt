package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.edit_person_data

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.edit_person_data.patient.EditPersonDataPatientScreen

@Composable
fun EditPersonDataScreen(
    navController: NavHostController,
    viewModel: EditPersonDataViewModel = hiltViewModel()
) {
    if (viewModel.state.isPatientUser)
        EditPersonDataPatientScreen(navController = navController)
}