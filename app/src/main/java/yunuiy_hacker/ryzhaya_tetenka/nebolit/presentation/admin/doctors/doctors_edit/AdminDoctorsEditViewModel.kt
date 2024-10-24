package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.doctors.doctors_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.dateToString
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.isEmail
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.AdminDoctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case.GetAllSpecializationsUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case.UpdateAdminDoctorUseCase
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AdminDoctorsEditViewModel @Inject constructor(
    val dataStoreHelper: DataStoreHelper,
    private val getAllSpecializationsUseCase: GetAllSpecializationsUseCase,
    private val updateAdminDoctorUseCase: UpdateAdminDoctorUseCase
) :
    ViewModel() {
    val state by mutableStateOf(AdminDoctorsEditState())

    fun onEvent(event: AdminDoctorsEditEvent) {
        when (event) {
            is AdminDoctorsEditEvent.ValidateEvent -> validate()

            is AdminDoctorsEditEvent.GetAllSpecializations -> getAllSpecializations()

            is AdminDoctorsEditEvent.ChangeEmailEvent -> {
                state.email = event.email
                validate()
            }

            is AdminDoctorsEditEvent.ChangePasswordEvent -> {
                state.password = event.password
                validate()
            }

            is AdminDoctorsEditEvent.ChangeSurnameEvent -> {
                state.surname = event.surname
                validate()
            }

            is AdminDoctorsEditEvent.ChangeNameEvent -> {
                state.name = event.name
                validate()
            }

            is AdminDoctorsEditEvent.ChangeLastnameEvent -> {
                state.lastname = event.lastname
                validate()
            }

            is AdminDoctorsEditEvent.ChangeAddressOfBirthEvent -> {
                state.addressOfBirth = event.addressOfBirth
                validate()
            }

            is AdminDoctorsEditEvent.ChangeSeriesEvent -> {
                state.series = event.series
                validate()
            }

            is AdminDoctorsEditEvent.ChangeCodeEvent -> {
                state.code = event.code
                validate()
            }

            is AdminDoctorsEditEvent.ChangeSexEvent -> {
                state.sex = event.sex
                validate()
            }

            is AdminDoctorsEditEvent.ChangeIssueOrganizationEvent -> {
                state.issueOrganization = event.issueOrganization
                validate()
            }

            is AdminDoctorsEditEvent.ChangeDepartmentCodeEvent -> {
                state.departmentCode = event.departmentCode
                validate()
            }

            is AdminDoctorsEditEvent.ChangeSpecializationIndexEvent -> {
                state.specializationIndex = event.specializationIndex
                validate()
            }

            is AdminDoctorsEditEvent.ChangeLicenzeNumberEvent -> {
                state.licenzeNumber = event.licenzeNumber
                validate()
            }

            is AdminDoctorsEditEvent.ShowDateOfBirthPickerDialogEvent -> state.nowSelectableDateOfBirth =
                true

            is AdminDoctorsEditEvent.ShowIssueDatePickerDialogEvent -> state.nowSelectableIssueDate =
                true

            is AdminDoctorsEditEvent.HideDatePickerDialogEvent -> {
                state.nowSelectableDateOfBirth = false
                state.nowSelectableIssueDate = false
                validate()
            }

            is AdminDoctorsEditEvent.OnClickButtonEvent -> editPersonDataOfDoctor()

            is AdminDoctorsEditEvent.ShowDialogEvent -> state.showDialog = true
            is AdminDoctorsEditEvent.HideDialogEvent -> state.showDialog = false
        }
    }

    private fun getAllSpecializations() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {
                    state.specializations = getAllSpecializationsUseCase.execute().toMutableList()

                    state.specializationIndex =
                        state.specializations.indexOfLast { it.title == state.adminDoctor.specializationTitle }
                    state.contentState.isLoading.value = false
                } catch (e: Exception) {
                    state.contentState.isLoading.value = false
                }
            }
        }
    }

    private fun validate() {
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
                                                if (state.specializationIndex != -2) {
                                                    state.specializationValid = true
                                                    if (state.licenzeNumber.length == 15) {
                                                        state.licenzeNumberValid = true
                                                    } else state.licenzeNumberValid = false
                                                } else state.specializationValid = false
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

        if (state.emailValid && state.passwordValid && state.surnameValid && state.nameValid && state.lastnameValid && state.addressOfBirthValid && state.seriesValid && state.codeValid && state.issueOrganizationValid && state.departmentCodeValid && state.specializationValid && state.licenzeNumberValid) state.valid =
            true
        else state.valid = false
    }

    fun editPersonDataOfDoctor() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {
                    if (
                        updateAdminDoctorUseCase.execute(
                            adminDoctor = AdminDoctor(
                                user_id = state.adminDoctor.user_id,
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
                                specializationId = state.specializations[state.specializationIndex].id!!,
                                licenzeNumber = state.licenzeNumber
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