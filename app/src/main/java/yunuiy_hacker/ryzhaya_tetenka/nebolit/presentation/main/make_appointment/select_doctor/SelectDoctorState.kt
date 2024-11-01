package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.make_appointment.select_doctor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Doctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.state.ContentState

class SelectDoctorState {
    var contentState by mutableStateOf(ContentState())
    var showDialog by mutableStateOf(false)

    var specializationId by mutableStateOf(0)
    var doctorId by mutableStateOf(0)

    var doctors: MutableList<Doctor> = mutableStateListOf()
}