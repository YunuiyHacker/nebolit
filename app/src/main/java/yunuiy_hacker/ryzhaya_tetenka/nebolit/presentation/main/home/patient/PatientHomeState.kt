package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.home.patient

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Appointment
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient

class PatientHomeState {
    var patient by mutableStateOf(Patient())
    var appointments: MutableList<Appointment> = mutableStateListOf()
}