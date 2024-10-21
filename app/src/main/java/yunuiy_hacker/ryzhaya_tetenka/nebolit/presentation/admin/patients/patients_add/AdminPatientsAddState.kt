package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.patients.patients_add

import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Passport
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.date_picker.SelectableNonFutureDates
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.state.ContentState

class AdminPatientsAddState {
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var surname by mutableStateOf("")
    var name by mutableStateOf("")
    var lastname by mutableStateOf("")

    @OptIn(ExperimentalMaterial3Api::class)
    var dateOfBirth by mutableStateOf(
        DatePickerState(
            CalendarLocale("ru"),
            selectableDates = SelectableNonFutureDates
        )
    )
    var addressOfBirth by mutableStateOf("")
    var series by mutableStateOf("")
    var code by mutableStateOf("")
    var sex by mutableStateOf(true)

    @OptIn(ExperimentalMaterial3Api::class)
    var issueDate by mutableStateOf(
        DatePickerState(
            CalendarLocale("ru"),
            selectableDates = SelectableNonFutureDates
        )
    )
    var issueOrganization by mutableStateOf("")
    var departmentCode by mutableStateOf("")

    var registrationAddress by mutableStateOf("")
    var liveAddress by mutableStateOf("")
    var policy by mutableStateOf("")
    var insuranceCompany by mutableStateOf("")
    var height by mutableIntStateOf(0)
    var weight by mutableFloatStateOf(0.0f)

    var nowSelectableDateOfBirth by mutableStateOf(false)
    var nowSelectableIssueDate by mutableStateOf(false)

    var valid by mutableStateOf(false)

    var contentState by mutableStateOf(ContentState())
    var showDialog by mutableStateOf(false)

    var success by mutableStateOf(false)

    var user by mutableStateOf(User())
}