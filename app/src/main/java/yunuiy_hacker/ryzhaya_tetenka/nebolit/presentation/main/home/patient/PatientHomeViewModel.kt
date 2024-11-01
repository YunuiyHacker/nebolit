package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.home.patient

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SaveReadPersonDataUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.common.use_case.DefineTimeOfDayUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.patient.use_case.GetPatientAppointmentsUseCase
import javax.inject.Inject

@HiltViewModel
class PatientHomeViewModel @Inject constructor(
    val defineTimeOfDayUseCase: DefineTimeOfDayUseCase,
    val dataStoreHelper: DataStoreHelper,
    private val saveReadPersonDataUseCase: SaveReadPersonDataUseCase,
    private val getPatientAppointmentsUseCase: GetPatientAppointmentsUseCase
) : ViewModel() {
    val state by mutableStateOf(PatientHomeState())

    init {
        state.patient = saveReadPersonDataUseCase.readPatient.invoke()!!
    }

    fun onEvent(event: PatientHomeEvent) {
        when (event) {
            is PatientHomeEvent.GetAppointmentsEvent -> getAppointments()

            is PatientHomeEvent.ShowDialogEvent -> state.showDialog = true
            is PatientHomeEvent.HideDialogEvent -> state.showDialog = false
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getAppointments() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {
                    state.appointments =
                        getPatientAppointmentsUseCase.execute(state.patient.id!!).toMutableList()

                    state.contentState.isLoading.value = false
                } catch (e: Exception) {
                    state.contentState.isLoading.value = false
                    state.contentState.exception.value = e
                    state.showDialog = true
                }
            }
        }
    }
}