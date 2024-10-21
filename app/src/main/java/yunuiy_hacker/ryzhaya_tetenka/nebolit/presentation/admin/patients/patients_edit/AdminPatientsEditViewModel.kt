package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.patients.patients_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.dateToLocalDateString
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.dateToString
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.isEmail
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toDateOfMobileFormat
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.AdminPatient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case.UpdateAdminPatientUseCase
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AdminPatientsEditViewModel @Inject constructor(
    val dataStoreHelper: DataStoreHelper,
    private val updateAdminPatientUseCase: UpdateAdminPatientUseCase
) : ViewModel() {
    val state by mutableStateOf(AdminPatientsEditState())

    fun onEvent(event: AdminPatientsEditEvent) {
        when (event) {
            is AdminPatientsEditEvent.ValidateEvent -> validate()

            is AdminPatientsEditEvent.ChangeEmailEvent -> {
                state.email = event.email
                validate()
            }

            is AdminPatientsEditEvent.ChangePasswordEvent -> {
                state.password = event.password
                validate()
            }

            is AdminPatientsEditEvent.ChangeSurnameEvent -> {
                state.surname = event.surname
                validate()
            }

            is AdminPatientsEditEvent.ChangeNameEvent -> {
                state.name = event.name
                validate()
            }

            is AdminPatientsEditEvent.ChangeLastnameEvent -> {
                state.lastname = event.lastname
                validate()
            }

            is AdminPatientsEditEvent.ChangeAddressOfBirthEvent -> {
                state.addressOfBirth = event.addressOfBirth
                validate()
            }

            is AdminPatientsEditEvent.ChangeSeriesEvent -> {
                state.series = event.series
                validate()
            }

            is AdminPatientsEditEvent.ChangeCodeEvent -> {
                state.code = event.code
                validate()
            }

            is AdminPatientsEditEvent.ChangeSexEvent -> {
                state.sex = event.sex
                validate()
            }

            is AdminPatientsEditEvent.ChangeIssueOrganizationEvent -> {
                state.issueOrganization = event.issueOrganization
                validate()
            }

            is AdminPatientsEditEvent.ChangeDepartmentCodeEvent -> {
                state.departmentCode = event.departmentCode
                validate()
            }

            is AdminPatientsEditEvent.ChangeRegistrationAddressEvent -> {
                state.registrationAddress = event.registrationAddress
                validate()
            }

            is AdminPatientsEditEvent.ChangeLiveAddressEvent -> {
                state.liveAddress = event.liveAddress
                validate()
            }

            is AdminPatientsEditEvent.ChangePolicyEvent -> {
                state.policy = event.policy
                validate()
            }

            is AdminPatientsEditEvent.ChangeInsuranceCompanyEvent -> {
                state.insuranceCompany = event.insuranceCompany
                validate()
            }

            is AdminPatientsEditEvent.ChangeHeightEvent -> {
                state.height = event.height
                validate()
            }

            is AdminPatientsEditEvent.ChangeWeightEvent -> {
                state.weight = event.weight
                validate()
            }

            is AdminPatientsEditEvent.ShowDateOfBirthPickerDialogEvent -> state.nowSelectableDateOfBirth =
                true

            is AdminPatientsEditEvent.ShowIssueDatePickerDialogEvent -> state.nowSelectableIssueDate =
                true

            is AdminPatientsEditEvent.HideDatePickerDialogEvent -> {
                state.nowSelectableDateOfBirth = false
                state.nowSelectableIssueDate = false
                validate()
            }

            is AdminPatientsEditEvent.OnClickButtonEvent -> editPersonDataOfPatient()

            is AdminPatientsEditEvent.ShowDialogEvent -> state.showDialog = true
            is AdminPatientsEditEvent.HideDialogEvent -> state.showDialog = false
        }
    }

    fun validate() {
        if (state.email.isEmail()) {
            state.emailValid = true
            if (state.password.length >= 8) {
                state.passwordValid = true
                if (state.surname.length >= 2) {
                    state.surnameValid = true
                    if (state.name.length >= 2) {
                        state.nameValid = true
                        if (state.lastname.length >= 2) {
                            state.lastnameValid = true
                            if (state.addressOfBirth.length >= 2) {
                                state.addressOfBirthValid = true
                                if (state.series.length == 4) {
                                    state.seriesValid = true
                                    if (state.code.length == 6) {
                                        state.codeValid = true
                                        if (state.issueOrganization.length >= 2) {
                                            state.issueOrganizationValid = true
                                            if (state.departmentCode.length == 7) {
                                                state.departmentCodeValid = true
                                                if (state.registrationAddress.length > 0) {
                                                    state.registrationAddressValid = true
                                                    if (state.liveAddress.length > 0) {
                                                        state.liveAddressValid = true
                                                        if (state.policy.length == 16) {
                                                            state.policyValid = true
                                                            if (state.insuranceCompany.length >= 3) {
                                                                state.insuranceCompanyValid = true
                                                                if (state.height >= 30 && state.height <= 220) {
                                                                    state.heightValid = true
                                                                    if (state.weight >= 2.5 && state.weight <= 300) {
                                                                        state.weightValid = true
                                                                    } else state.weightValid = false
                                                                } else state.heightValid = false
                                                            } else state.insuranceCompanyValid =
                                                                false
                                                        } else state.policyValid = false
                                                    } else state.liveAddressValid = false
                                                } else state.registrationAddressValid = false
                                            } else state.departmentCodeValid = false
                                        } else state.issueOrganizationValid = false
                                    } else state.codeValid = false
                                } else state.seriesValid = false
                            } else state.addressOfBirthValid = false
                        } else state.lastnameValid = false
                    } else state.nameValid = false
                } else state.surnameValid = false
            } else state.passwordValid = false
        } else state.emailValid = false

        if (state.emailValid && state.passwordValid && state.surnameValid && state.nameValid && state.lastnameValid && state.addressOfBirthValid && state.seriesValid && state.codeValid && state.issueOrganizationValid && state.departmentCodeValid && state.registrationAddressValid && state.liveAddressValid && state.policyValid && state.insuranceCompanyValid && state.heightValid && state.weightValid) state.valid =
            true
        else state.valid = false
    }

    fun editPersonDataOfPatient() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {
                    if (
                        updateAdminPatientUseCase.execute(
                            adminPatient = AdminPatient(
                                user_id = state.adminPatient.user_id,
                                email = state.email,
                                password = state.password,
                                surname = state.surname,
                                name = state.name,
                                lastname = state.lastname,
                                dateOfBirth = Date(state.dateOfBirth.selectedDateMillis!!).dateToString(),
                                addressOfBirth = state.addressOfBirth,
                                series = state.series.toInt(),
                                code = state.code.toInt(),
                                sex = state.sex,
                                issueDate = Date(state.issueDate.selectedDateMillis!!).dateToString(),
                                issueOrganization = state.issueOrganization,
                                departmentCode = state.departmentCode,
                                registrationAddress = state.registrationAddress,
                                liveAddress = state.liveAddress,
                                insuranceCompany = state.insuranceCompany,
                                policy = state.policy,
                                height = state.height,
                                weight = state.weight
                            )
                        )
                    ) {
                        state.contentState.isLoading.value = false
                        state.success = true
                    } else {
                        throw Exception("При сохранении изменений произошла ошибка")
                    }
                } catch (e: Exception) {
                    state.contentState.isLoading.value = true
                    state.contentState.exception.value = e
                    state.showDialog = true
                }
            }
        }
    }
}