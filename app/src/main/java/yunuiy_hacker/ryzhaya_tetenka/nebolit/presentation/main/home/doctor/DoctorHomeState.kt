package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.home.doctor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DoctorSchedule
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.state.ContentState

class DoctorHomeState {
    var doctorSchedules: MutableList<DoctorSchedule> = mutableStateListOf()
    var doctorSchedule by mutableStateOf(DoctorSchedule())
    var patient by mutableStateOf(Patient())

    var contentState by mutableStateOf(ContentState())
    var showPatientInfo by mutableStateOf(false)
    var showDialog by mutableStateOf(false)
}