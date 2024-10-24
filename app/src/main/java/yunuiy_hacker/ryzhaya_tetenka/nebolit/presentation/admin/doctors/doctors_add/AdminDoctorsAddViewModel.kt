package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.doctors.doctors_add

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Doctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Passport
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.isEmail
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toLocalDate
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.RegistrationDoctorModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case.GetAllSpecializationsUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case.RegistrationDoctorUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.RecordPassportModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.SignUpModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.UpdateUserPassportIdModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.RecordPassportUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SignUpUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.UpdateUserPassportIdUseCase
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AdminDoctorsAddViewModel @Inject constructor(
    val dataStoreHelper: DataStoreHelper,
    private val signUpUseCase: SignUpUseCase,
    private val updateUserPassportIdUseCase: UpdateUserPassportIdUseCase,
    private val recordPassportUseCase: RecordPassportUseCase,
    private val registrationDoctorUseCase: RegistrationDoctorUseCase,
    val getAllSpecializationsUseCase: GetAllSpecializationsUseCase
) :
    ViewModel() {
    val state by mutableStateOf(AdminDoctorsAddState())

    fun onEvent(event: AdminDoctorsAddEvent) {
        when (event) {
            is AdminDoctorsAddEvent.GetAllSpecializations -> getAllSpecializations()

            is AdminDoctorsAddEvent.ChangeEmailEvent -> {
                state.email = event.email
                validate()
            }

            is AdminDoctorsAddEvent.ChangePasswordEvent -> {
                state.password = event.password
                validate()
            }

            is AdminDoctorsAddEvent.ChangeSurnameEvent -> {
                state.surname = event.surname
                validate()
            }

            is AdminDoctorsAddEvent.ChangeNameEvent -> {
                state.name = event.name
                validate()
            }

            is AdminDoctorsAddEvent.ChangeLastnameEvent -> {
                state.lastname = event.lastname
                validate()
            }

            is AdminDoctorsAddEvent.ChangeAddressOfBirthEvent -> {
                state.addressOfBirth = event.addressOfBirth
                validate()
            }

            is AdminDoctorsAddEvent.ChangeSeriesEvent -> {
                state.series = event.series
                validate()
            }

            is AdminDoctorsAddEvent.ChangeCodeEvent -> {
                state.code = event.code
                validate()
            }

            is AdminDoctorsAddEvent.ChangeSexEvent -> {
                state.sex = event.sex
                validate()
            }

            is AdminDoctorsAddEvent.ChangeIssueOrganizationEvent -> {
                state.issueOrganization = event.issueOrganization
                validate()
            }

            is AdminDoctorsAddEvent.ChangeDepartmentCodeEvent -> {
                state.departmentCode = event.departmentCode
                validate()
            }

            is AdminDoctorsAddEvent.ChangeSpecializationIndexEvent -> {
                state.specializationIndex = event.specializationIndex
                validate()
            }

            is AdminDoctorsAddEvent.ChangeLicenzeNumberEvent -> {
                state.licenzeNumber = event.licenzeNumber
                validate()
            }

            is AdminDoctorsAddEvent.ShowDateOfBirthPickerDialogEvent -> state.nowSelectableDateOfBirth =
                true

            is AdminDoctorsAddEvent.ShowIssueDatePickerDialogEvent -> state.nowSelectableIssueDate =
                true

            is AdminDoctorsAddEvent.HideDatePickerDialogEvent -> {
                state.nowSelectableDateOfBirth = false
                state.nowSelectableIssueDate = false
                validate()
            }

            is AdminDoctorsAddEvent.OnClickButtonEvent -> registrationDoctor()

            is AdminDoctorsAddEvent.ShowDialogEvent -> state.showDialog = true
            is AdminDoctorsAddEvent.HideDialogEvent -> state.showDialog = false
        }
    }

    private fun getAllSpecializations() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {
                    state.specializations = getAllSpecializationsUseCase.execute().toMutableList()

                    state.contentState.isLoading.value = false
                } catch (e: Exception) {
                    state.contentState.isLoading.value = false
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun validate() {
        if (state.email.isEmail()) if (state.password.length >= 8) if (state.surname.length >= 2) if (state.name.length >= 2) if (state.lastname.length >= 2) if (state.dateOfBirth.selectedDateMillis != null) if (state.addressOfBirth.length > 0) if (state.series.length == 4) if (state.code.length == 6) if (state.issueDate.selectedDateMillis != null) if (state.issueOrganization.length > 0) if (state.departmentCode.length == 7) if (state.specializationIndex != -2) if (state.licenzeNumber.length == 15) state.valid =
            true
        else state.valid = false
        else state.valid = false
        else state.valid = false
        else state.valid = false
        else state.valid = false
        else state.valid = false
        else state.valid = false
        else state.valid = false
        else state.valid = false
        else state.valid = false
        else state.valid = false
        else state.valid = false
        else state.valid = false
        else state.valid = false
    }

    @OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
    private fun registrationDoctor() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {
                    val signUpModel: SignUpModel =
                        SignUpModel(email = state.email, password = state.password)

                    if (signUpUseCase.checkRegistrationByEmail.invoke(signUpModel)) {
                        val user = signUpUseCase.registrationUser.invoke(signUpModel)
                        if (user == null)
                            throw Exception("При регистрации пользователя произошла ошибка")
                        else {
                            val passport: Passport? = recordPassportUseCase.execute(
                                recordPassportModel =
                                RecordPassportModel(
                                    surname = state.surname,
                                    name = state.name,
                                    lastname = state.lastname,
                                    dateOfBirth = Date(state.dateOfBirth.selectedDateMillis!!).toLocalDate(),
                                    addressOfBirth = state.addressOfBirth,
                                    series = state.series,
                                    code = state.code,
                                    sex = state.sex,
                                    issueDate = Date(state.issueDate.selectedDateMillis!!).toLocalDate(),
                                    issueOrganization = state.issueOrganization,
                                    departamentCode = state.departmentCode
                                )
                            )

                            if (passport == null) throw Exception("Не удалось добавить паспорт для пользователя")
                            else {
                                val updatedUser: User? = updateUserPassportIdUseCase.invoke(
                                    updateUserPassportModel = UpdateUserPassportIdModel(
                                        userId = user.id!!,
                                        passportId = passport.id!!
                                    )
                                )

                                if (updatedUser == null) throw Exception("При добавлении паспортных данных к пользователю произошла ошибка")
                                else {
                                    val doctor: Doctor? = registrationDoctorUseCase.execute(
                                        registrationDoctorModel = RegistrationDoctorModel(
                                            user_id = user.id!!,
                                            specializationId = state.specializations[state.specializationIndex].id!!,
                                            licenzeNumber = state.licenzeNumber
                                        )
                                    )
//
                                    if (doctor == null) throw Exception("При регистрации врача в системе произошла ошибка")
                                    else {
                                        state.success = true
                                    }
                                    state.contentState.isLoading.value = false
                                }
                            }
                        }
                    } else {
                        throw Exception("Пользователь с такой почтой уже зарегистрирован")
                    }
                } catch (e: Exception) {
                    state.contentState.exception.value = e
                    state.contentState.isLoading.value = false
                    state.showDialog = true
                }
            }
        }
    }
}