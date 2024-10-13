package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.fill_person_data

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
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toLocalDate
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.RecordPassportModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.RegistrationPatientModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.UpdateUserPassportIdModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.RecordPassportUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.RegistrationPatientUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SaveReadPersonDataUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.UpdateUserPassportIdUseCase
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class FillPersonDataViewModel @Inject constructor(
    private val registrationPatientUseCase: RegistrationPatientUseCase,
    private val recordPassportUseCase: RecordPassportUseCase,
    private val updateUserPassportIdUseCase: UpdateUserPassportIdUseCase,
    private val saveReadPersonDataUseCase: SaveReadPersonDataUseCase,
    val dataStoreHelper: DataStoreHelper
) : ViewModel() {
    val state by mutableStateOf(FillPersonDataState())

    fun onEvent(event: FillPersonDataEvent) {
        when (event) {
            is FillPersonDataEvent.ChangeSurnameEvent -> {
                state.surname = event.surname
                validate()
            }

            is FillPersonDataEvent.ChangeNameEvent -> {
                state.name = event.name
                validate()
            }

            is FillPersonDataEvent.ChangeLastnameEvent -> {
                state.lastname = event.lastname
                validate()
            }

            is FillPersonDataEvent.ChangeAddressOfBirthEvent -> {
                state.addressOfBirth = event.addressOfBirth
                validate()
            }

            is FillPersonDataEvent.ChangeSeriesEvent -> {
                state.series = event.series
                validate()
            }

            is FillPersonDataEvent.ChangeCodeEvent -> {
                state.code = event.code
                validate()
            }

            is FillPersonDataEvent.ChangeSexEvent -> {
                state.sex = event.sex
                validate()
            }

            is FillPersonDataEvent.ChangeIssueOrganizationEvent -> {
                state.issueOrganization = event.issueOrganization
                validate()
            }

            is FillPersonDataEvent.ChangeDepartmentCodeEvent -> {
                state.departmentCode = event.departmentCode
                validate()
            }

            is FillPersonDataEvent.ChangeRegistrationAddressEvent -> {
                state.registrationAddress = event.registrationAddress
                validate()
            }

            is FillPersonDataEvent.ChangeLiveAddressEvent -> {
                state.liveAddress = event.liveAddress
                validate()
            }

            is FillPersonDataEvent.ChangePolicyEvent -> {
                state.policy = event.policy
                validate()
            }

            is FillPersonDataEvent.ChangeInsuranceCompanyEvent -> {
                state.insuranceCompany = event.insuranceCompany
                validate()
            }

            is FillPersonDataEvent.ChangeHeightEvent -> {
                state.height = event.height
                validate()
            }

            is FillPersonDataEvent.ChangeWeightEvent -> {
                state.weight = event.weight
                validate()
            }

            is FillPersonDataEvent.ShowDateOfBirthPickerDialogEvent -> state.nowSelectableDateOfBirth =
                true

            is FillPersonDataEvent.ShowIssueDatePickerDialogEvent -> state.nowSelectableIssueDate = true
            is FillPersonDataEvent.HideDatePickerDialogEvent -> {
                state.nowSelectableDateOfBirth = false
                state.nowSelectableIssueDate = false
                validate()
            }

            is FillPersonDataEvent.OnClickButtonEvent -> registrationPatient()

            is FillPersonDataEvent.ShowDialogEvent -> state.showDialog = true
            is FillPersonDataEvent.HideDialogEvent -> state.showDialog = false
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun validate() {
        if (state.surname.length >= 2) if (state.name.length >= 2) if (state.lastname.length >= 2) if (state.dateOfBirth.selectedDateMillis != null) if (state.addressOfBirth.length > 0) if (state.series.length == 4) if (state.code.length == 6) if (state.issueDate.selectedDateMillis != null) if (state.issueOrganization.length > 0) if (state.departmentCode.length == 7) if (state.registrationAddress.length > 0) if (state.liveAddress.length > 0) if (state.policy.length == 16) if (state.height >= 30 && state.height <= 220) if (state.weight >= 2.5 && state.weight <= 300) state.valid =
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
    }

    @OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
    fun registrationPatient() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {
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

                    if (passport == null) throw Exception("К сожалению нам не удалось вас зарегистрировать, пожалуйста, попробуйте еще раз")
                    else {
                        state.passport = passport
                        saveReadPersonDataUseCase.savePassport.invoke(passport)

                        val user: User? = updateUserPassportIdUseCase.invoke(
                            updateUserPassportModel = UpdateUserPassportIdModel(
                                userId = state.userId,
                                passportId = passport.id!!
                            )
                        )

                        if (user == null) throw Exception("При добавлении паспортных данных к пользователю произошла ошибка")
                        else {
                            val patient: Patient? = registrationPatientUseCase.execute(
                                registrationPatientModel = RegistrationPatientModel(
                                    user_id = state.userId,
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
                                state.patient = patient
                                saveReadPersonDataUseCase.savePatient.invoke(patient)
                                state.success = true
                            }
                            state.contentState.isLoading.value = false
                        }
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