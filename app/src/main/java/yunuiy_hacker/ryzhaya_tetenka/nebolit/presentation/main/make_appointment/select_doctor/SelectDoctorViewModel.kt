package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.make_appointment.select_doctor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.patient.use_case.GetDoctorsBySpecializationUseCase
import javax.inject.Inject

@HiltViewModel
class SelectDoctorViewModel @Inject constructor(
    val dataStoreHelper: DataStoreHelper,
    private val getDoctorsBySpecializationUseCase: GetDoctorsBySpecializationUseCase
) :
    ViewModel() {
    val state by mutableStateOf(SelectDoctorState())

    fun onEvent(event: SelectDoctorEvent) {
        when (event) {
            is SelectDoctorEvent.GetDoctorsBySpecializationEvent -> getDoctorsBySpecialization()
            is SelectDoctorEvent.SelectCurrentDoctorEvent -> {
                state.doctorId = event.doctor.id!!
            }

            is SelectDoctorEvent.ShowDialogEvent -> state.showDialog = true
            is SelectDoctorEvent.HideDialogEvent -> state.showDialog = false
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getDoctorsBySpecialization() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {
                    state.doctors =
                        getDoctorsBySpecializationUseCase.execute(specializationId = state.specializationId)
                            .toMutableList()

                    state.contentState.isLoading.value = false
                } catch (e: Exception) {
                    state.contentState.isLoading.value = false
                }
            }
        }
    }

}