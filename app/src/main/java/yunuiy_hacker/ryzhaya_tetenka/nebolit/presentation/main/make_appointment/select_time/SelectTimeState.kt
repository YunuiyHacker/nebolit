package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.make_appointment.select_time

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.state.ContentState

class SelectTimeState {
    var contentState by mutableStateOf(ContentState())
    var showDialog by mutableStateOf(false)

    var doctorId by mutableStateOf(0)
}