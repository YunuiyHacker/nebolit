package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.fill_person_data

import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.date_picker.SelectableNonFutureDates
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.state.ContentState

class FillPersonDataState {
    @OptIn(ExperimentalMaterial3Api::class)
    var dateOfBirth by mutableStateOf(
        DatePickerState(
            CalendarLocale("ru"),
            selectableDates = SelectableNonFutureDates
        )
    )
    var sex by mutableStateOf(true)
    var registrationAddress by mutableStateOf("")
    var liveAddress by mutableStateOf("")
    var policy by mutableStateOf("")
    var insuranceCompany by mutableStateOf("")
    var height by mutableIntStateOf(0)
    var weight by mutableFloatStateOf(0.0f)

    var showDatePickerDialog by mutableStateOf(false)

    var valid by mutableStateOf(false)

    var contentState by mutableStateOf(ContentState())
    var showDialog by mutableStateOf(false)

    var success by mutableStateOf(false)

    var user_id by mutableIntStateOf(0)
    var patient by mutableStateOf(Patient())
}