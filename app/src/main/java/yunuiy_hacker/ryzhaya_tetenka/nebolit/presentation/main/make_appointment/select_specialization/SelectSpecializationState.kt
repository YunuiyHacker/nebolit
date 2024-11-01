package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.make_appointment.select_specialization

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Specialization
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.state.ContentState

class SelectSpecializationState {
    var contentState by mutableStateOf(ContentState())
    var showDialog by mutableStateOf(false)

    var specialization by mutableStateOf(Specialization())

    var specializations: MutableList<Specialization> = mutableStateListOf()
}