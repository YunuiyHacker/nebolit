package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.profile.patient

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient

class PatientProfileState {
    var isDarkTheme by mutableStateOf(false)

    var passportVisible by mutableStateOf(false)
    var moreInformationVisible by mutableStateOf(false)

    var settingsVisible by mutableStateOf(false)

    var patient by mutableStateOf(Patient())
}