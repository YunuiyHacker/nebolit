package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.fill_person_data

import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.date_picker.SelectableNonFutureDates

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
    var height by mutableStateOf("")
    var weight by mutableStateOf("")

    var showDatePickerDialog by mutableStateOf(false)

    var valid by mutableStateOf(false)
}