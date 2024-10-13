package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.profile.doctor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Doctor

class DoctorProfileState {
    var passportVisible by mutableStateOf(false)

    var settingsVisible by mutableStateOf(false)

    var doctor by mutableStateOf(Doctor())
}