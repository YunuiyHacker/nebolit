package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.edit_person_data.patient

import androidx.compose.material3.DatePickerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toDateOfBackendFormat
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toLocalDate
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.RecordPassportUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.RegistrationPatientUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SaveReadPersonDataUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.UpdateUserPassportIdUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.patient.model.UpdatePatientDataModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.patient.use_case.UpdatePatientDataUseCase
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class EditPersonDataPatientViewModel @Inject constructor(
    private val saveReadPersonDataUseCase: SaveReadPersonDataUseCase,
    private val updatePatientDataUseCase: UpdatePatientDataUseCase,
    private val sharedPrefsHelper: SharedPrefsHelper,
    val dataStoreHelper: DataStoreHelper
) : ViewModel() {
    val state by mutableStateOf(EditPersonDataPatientState())

    init {
        state.surname = sharedPrefsHelper.surname!!
        state.name = sharedPrefsHelper.name!!
        state.lastname = sharedPrefsHelper.lastname!!
        state.dateOfBirth.selectedDateMillis =
            sharedPrefsHelper.dateOfBirth?.toDateOfBackendFormat()?.time
        state.registrationAddress = sharedPrefsHelper.addressOfBirth!!
        state.liveAddress = sharedPrefsHelper.live_address!!
        state.policy = sharedPrefsHelper.policy!!
        state.insuranceCompany = sharedPrefsHelper.insurance_company!!
        state.height = sharedPrefsHelper.height
        state.weight = sharedPrefsHelper.weight

        validate()
    }

    fun onEvent(event: EditPersonDataPatientEvent) {
        when (event) {
            is EditPersonDataPatientEvent.ChangeSurnameEvent -> {
                state.surname = event.surname
                validate()
            }

            is EditPersonDataPatientEvent.ChangeNameEvent -> {
                state.name = event.name
                validate()
            }

            is EditPersonDataPatientEvent.ChangeLastnameEvent -> {
                state.lastname = event.lastname
                validate()
            }

            is EditPersonDataPatientEvent.ChangeRegistrationAddressEvent -> {
                state.registrationAddress = event.registrationAddress
                validate()
            }

            is EditPersonDataPatientEvent.ChangeLiveAddressEvent -> {
                state.liveAddress = event.liveAddress
                validate()
            }

            is EditPersonDataPatientEvent.ChangePolicyEvent -> {
                state.policy = event.policy
                validate()
            }

            is EditPersonDataPatientEvent.ChangeInsuranceCompanyEvent -> {
                state.insuranceCompany = event.insuranceCompany
                validate()
            }

            is EditPersonDataPatientEvent.ChangeHeightEvent -> {
                state.height = event.height
                validate()
            }

            is EditPersonDataPatientEvent.ChangeWeightEvent -> {
                state.weight = event.weight
                validate()
            }

            is EditPersonDataPatientEvent.ShowDateOfBirthPickerDialogEvent -> state.nowSelectableDateOfBirth =
                true

            is EditPersonDataPatientEvent.ShowIssueDatePickerDialogEvent -> state.nowSelectableIssueDate =
                true

            is EditPersonDataPatientEvent.HideDatePickerDialogEvent -> {
                state.nowSelectableDateOfBirth = false
                state.nowSelectableIssueDate = false
                validate()
            }

            is EditPersonDataPatientEvent.OnClickButtonEvent -> editPersonDataOfPatient()

            is EditPersonDataPatientEvent.ShowDialogEvent -> state.showDialog = true
            is EditPersonDataPatientEvent.HideDialogEvent -> state.showDialog = false
        }
    }

    fun validate() {
        if (state.surname.length >= 2) {
            state.surnameValid = true
            if (state.name.length >= 2) {
                state.nameValid = true
                if (state.lastname.length >= 2) {
                    state.lastnameValid = true
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
                                } else state.insuranceCompanyValid = false
                            } else state.policyValid = false
                        } else state.liveAddressValid = false
                    } else state.registrationAddressValid = false
                } else state.lastnameValid = false
            } else state.nameValid = false
        } else state.surnameValid = false

        if (state.surnameValid && state.nameValid && state.lastnameValid && state.registrationAddressValid && state.liveAddressValid && state.policyValid && state.insuranceCompanyValid && state.heightValid && state.weightValid) state.valid =
            true
        else state.valid = false
    }

    fun editPersonDataOfPatient() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {
                    val updatePatientDataResultModel = updatePatientDataUseCase.execute(
                        updatePatientDataModel = UpdatePatientDataModel(
                            surname = state.surname,
                            name = state.name,
                            lastname = state.lastname,
                            dateOfBirth = Date(state.dateOfBirth.selectedDateMillis!!),
                            registrationAddress = state.registrationAddress,
                            liveAddress = state.liveAddress,
                            policy = state.policy,
                            insuranceCompany = state.insuranceCompany,
                            height = state.height,
                            weight = state.weight
                        )
                    )

                    if (updatePatientDataResultModel == null) {
                        throw Exception("К сожалению нам не удалось сохранить информацию")
                    } else {
                        if (updatePatientDataResultModel.passport == null) {
                            throw Exception("К сожалению нам не удалось сохранить ваши паспортные данные")
                        } else {
                            saveReadPersonDataUseCase.savePassport(updatePatientDataResultModel.passport!!)
                        }
                        if (updatePatientDataResultModel.patient == null) {
                            throw Exception("К сожалению нам не удалось сохранить ваши данные")
                        } else {
                            saveReadPersonDataUseCase.savePatient(updatePatientDataResultModel.patient!!)
                        }
                        state.contentState.isLoading.value = false
                        state.success = true
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