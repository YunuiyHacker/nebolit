package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.patients.patients_add

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Passport
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.isEmail
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toLocalDate
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.RecordPassportModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.RegistrationPatientModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.SignUpModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.UpdateUserPassportIdModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.RecordPassportUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.RegistrationPatientUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SaveReadPersonDataUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SignUpUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.UpdateUserPassportIdUseCase
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AdminPatientsAddViewModel @Inject constructor(
    val dataStoreHelper: DataStoreHelper,
    private val signUpUseCase: SignUpUseCase,
    private val registrationPatientUseCase: RegistrationPatientUseCase,
    private val recordPassportUseCase: RecordPassportUseCase,
    private val updateUserPassportIdUseCase: UpdateUserPassportIdUseCase,
) : ViewModel() {
    val state by mutableStateOf(AdminPatientsAddState())

    fun onEvent(event: AdminPatientsAddEvent) {
        when (event) {
            is AdminPatientsAddEvent.ChangeEmailEvent -> {
                state.email = event.email
                validate()
            }

            is AdminPatientsAddEvent.ChangePasswordEvent -> {
                state.password = event.password
                validate()
            }

            is AdminPatientsAddEvent.ChangeSurnameEvent -> {
                state.surname = event.surname
                validate()
            }

            is AdminPatientsAddEvent.ChangeNameEvent -> {
                state.name = event.name
                validate()
            }

            is AdminPatientsAddEvent.ChangeLastnameEvent -> {
                state.lastname = event.lastname
                validate()
            }

            is AdminPatientsAddEvent.ChangeAddressOfBirthEvent -> {
                state.addressOfBirth = event.addressOfBirth
                validate()
            }

            is AdminPatientsAddEvent.ChangeSeriesEvent -> {
                state.series = event.series
                validate()
            }

            is AdminPatientsAddEvent.ChangeCodeEvent -> {
                state.code = event.code
                validate()
            }

            is AdminPatientsAddEvent.ChangeSexEvent -> {
                state.sex = event.sex
                validate()
            }

            is AdminPatientsAddEvent.ChangeIssueOrganizationEvent -> {
                state.issueOrganization = event.issueOrganization
                validate()
            }

            is AdminPatientsAddEvent.ChangeDepartmentCodeEvent -> {
                state.departmentCode = event.departmentCode
                validate()
            }

            is AdminPatientsAddEvent.ChangeRegistrationAddressEvent -> {
                state.registrationAddress = event.registrationAddress
                validate()
            }

            is AdminPatientsAddEvent.ChangeLiveAddressEvent -> {
                state.liveAddress = event.liveAddress
                validate()
            }

            is AdminPatientsAddEvent.ChangePolicyEvent -> {
                state.policy = event.policy
                validate()
            }

            is AdminPatientsAddEvent.ChangeInsuranceCompanyEvent -> {
                state.insuranceCompany = event.insuranceCompany
                validate()
            }

            is AdminPatientsAddEvent.ChangeHeightEvent -> {
                state.height = event.height
                validate()
            }

            is AdminPatientsAddEvent.ChangeWeightEvent -> {
                state.weight = event.weight
                validate()
            }

            is AdminPatientsAddEvent.ShowDateOfBirthPickerDialogEvent -> state.nowSelectableDateOfBirth =
                true

            is AdminPatientsAddEvent.ShowIssueDatePickerDialogEvent -> state.nowSelectableIssueDate =
                true

            is AdminPatientsAddEvent.HideDatePickerDialogEvent -> {
                state.nowSelectableDateOfBirth = false
                state.nowSelectableIssueDate = false
                validate()
            }

            is AdminPatientsAddEvent.OnClickButtonEvent -> registrationPatient()

            is AdminPatientsAddEvent.ShowDialogEvent -> state.showDialog = true
            is AdminPatientsAddEvent.HideDialogEvent -> state.showDialog = false
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun validate() {
        if (state.email.isEmail()) if (state.password.length >= 8) if (state.surname.length >= 2) if (state.name.length >= 2) if (state.lastname.length >= 2) if (state.dateOfBirth.selectedDateMillis != null) if (state.addressOfBirth.length > 0) if (state.series.length == 4) if (state.code.length == 6) if (state.issueDate.selectedDateMillis != null) if (state.issueOrganization.length > 0) if (state.departmentCode.length == 7) if (state.registrationAddress.length > 0) if (state.liveAddress.length > 0) if (state.policy.length == 16) if (state.height >= 30 && state.height <= 220) if (state.weight >= 2.5 && state.weight <= 300) state.valid =
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
        else state.valid = false
        else state.valid = false
        else state.valid = false
    }

    @OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
    fun registrationPatient() {
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
                                    val patient: Patient? = registrationPatientUseCase.execute(
                                        registrationPatientModel = RegistrationPatientModel(
                                            user_id = user.id!!,
                                            registraion_address = state.registrationAddress,
                                            live_address = state.liveAddress,
                                            policy = state.policy,
                                            insurance_company = state.insuranceCompany,
                                            height = state.height,
                                            weight = state.weight
                                        )
                                    )

                                    if (patient == null) throw Exception("При регистрации пациента в системе произошла ошибка")
                                    else {
                                        state.success = true
                                    }
                                    state.contentState.isLoading.value = false
                                }
                            }
                        }
                    }
                    else {
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