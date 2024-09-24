package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.fill_person_data

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toLocalDate
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.RegistrationPatientModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.RegistrationPatientUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SaveReadPersonDataUseCase
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class FillPersonDataViewModel @Inject constructor(
    private val registrationPatientUseCase: RegistrationPatientUseCase,
    private val saveReadPersonDataUseCase: SaveReadPersonDataUseCase
) :
    ViewModel() {
    val state by mutableStateOf(FillPersonDataState())

    fun onEvent(event: FillPersonDataEvent) {
        when (event) {
            is FillPersonDataEvent.ChangeSex -> state.sex = event.sex
            is FillPersonDataEvent.ChangeRegistrationAddress -> {
                state.registrationAddress = event.registrationAddress
                validate()
            }

            is FillPersonDataEvent.ChangeLiveAddress -> {
                state.liveAddress = event.liveAddress
                validate()
            }

            is FillPersonDataEvent.ChangePolicy -> {
                state.policy = event.policy
                validate()
            }

            is FillPersonDataEvent.ChangeInsuranceCompany -> {
                state.insuranceCompany = event.insuranceCompany
                validate()
            }

            is FillPersonDataEvent.ChangeHeight -> {
                state.height = event.height
                validate()
            }

            is FillPersonDataEvent.ChangeWeight -> {
                state.weight = event.weight
                validate()
            }

            is FillPersonDataEvent.ShowDatePickerDialog -> state.showDatePickerDialog = true
            is FillPersonDataEvent.HideDatePickerDialog -> {
                state.showDatePickerDialog = false
                validate()
            }

            is FillPersonDataEvent.OnClickButton -> registrationPatient()

            is FillPersonDataEvent.ShowDialog -> state.showDialog = true
            is FillPersonDataEvent.HideDialog -> state.showDialog = false
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun validate() {
        if (state.dateOfBirth.selectedDateMillis != null) if (state.registrationAddress.length > 0) if (state.liveAddress.length > 0) if (state.policy.length == 16) if (state.height >= 30 && state.height <= 220) if (state.weight >= 2.5 && state.weight <= 300) state.valid =
            true
        else state.valid = false
        else state.valid = false
        else state.valid = false
        else state.valid = false
        else state.valid = false
        else state.valid = false
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun registrationPatient() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {
                    val patient: Patient? = registrationPatientUseCase.execute(
                        registrationPatientModel = RegistrationPatientModel(
                            user_id = state.user_id,
                            date_of_birth = Date(state.dateOfBirth.selectedDateMillis!!).toLocalDate(),
                            sex = state.sex,
                            registraion_address = state.registrationAddress,
                            live_address = state.liveAddress,
                            policy = state.policy,
                            insurance_company = state.insuranceCompany,
                            height = state.height,
                            weight = state.weight
                        )
                    )
                    if (patient == null) throw Exception("К сожалению нам не удалось вас зарегистрировать, пожалуйста, попробуйте еще раз")
                    else {
                        state.patient = patient
                        saveReadPersonDataUseCase.savePatient.invoke(patient)
                        state.success = true
                    }
                    state.contentState.isLoading.value = false
                } catch (e: Exception) {
                    state.contentState.exception.value = e
                    state.contentState.isLoading.value = false
                    state.showDialog = true
                }
            }
        }
    }
}