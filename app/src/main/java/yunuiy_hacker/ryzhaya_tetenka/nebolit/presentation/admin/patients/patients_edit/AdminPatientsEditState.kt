package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.patients.patients_edit

import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.AdminPatient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.date_picker.SelectableNonFutureDates
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.state.ContentState

class AdminPatientsEditState {
    var adminPatient by mutableStateOf(AdminPatient())

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

    var emailValid by mutableStateOf(true)
    var passwordValid by mutableStateOf(true)
    var surnameValid by mutableStateOf(true)
    var nameValid by mutableStateOf(true)
    var lastnameValid by mutableStateOf(true)
    var dateOfBirthValid by mutableStateOf(true)
    var addressOfBirthValid by mutableStateOf(true)
    var seriesValid by mutableStateOf(true)
    var codeValid by mutableStateOf(true)
    var issueDateValid by mutableStateOf(true)
    var issueOrganizationValid by mutableStateOf(true)
    var departmentCodeValid by mutableStateOf(true)
    var registrationAddressValid by mutableStateOf(true)
    var liveAddressValid by mutableStateOf(true)
    var policyValid by mutableStateOf(true)
    var insuranceCompanyValid by mutableStateOf(true)
    var heightValid by mutableStateOf(true)
    var weightValid by mutableStateOf(true)

    var valid by mutableStateOf(true)

    var contentState by mutableStateOf(ContentState())
    var showDialog by mutableStateOf(false)

    var success by mutableStateOf(false)
}