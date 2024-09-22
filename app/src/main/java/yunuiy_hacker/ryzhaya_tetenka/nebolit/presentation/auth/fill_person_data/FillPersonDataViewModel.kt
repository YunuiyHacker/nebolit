package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.fill_person_data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FillPersonDataViewModel @Inject constructor() : ViewModel() {
    val state by mutableStateOf(FillPersonDataState())

    fun onEvent(event: FillPersonDataEvent) {
        when (event) {
            is FillPersonDataEvent.ChangeSex -> state.sex = event.sex
            is FillPersonDataEvent.ChangeRegistrationAddress -> {
                state.registrationAddress =
                    event.registrationAddress
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
                state.insuranceCompany =
                    event.insuranceCompany
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
        }
    }

    fun validate() {

    }

    fun registrationPatient() {

    }
}