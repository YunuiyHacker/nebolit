package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.make_appointment.select_time

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DoctorSchedule
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.state.ContentState

class SelectTimeState {
    var doctorSchedules: MutableList<DoctorSchedule> = mutableStateListOf()
    var doctorSchedule by mutableStateOf(DoctorSchedule())

    var contentState by mutableStateOf(ContentState())
    var showMakeAppointment by mutableStateOf(false)
    var showDialog by mutableStateOf(false)

    var doctorId by mutableStateOf(0)
}