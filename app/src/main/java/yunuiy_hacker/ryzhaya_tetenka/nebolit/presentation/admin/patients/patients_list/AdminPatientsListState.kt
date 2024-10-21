package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.patients.patients_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.AdminPatient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.state.ContentState

class AdminPatientsListState {
    var tableSelectedPatientIndex by mutableStateOf(-2)

    val contentState by mutableStateOf(ContentState())
    var showDialog by mutableStateOf(false)

    var success by mutableStateOf(false)

    var patients: MutableList<AdminPatient> = mutableListOf()
}