package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.home.patient

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SaveReadPersonDataUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.common.use_case.DefineTimeOfDayUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.patient.use_case.GetPatientAppointmentsUseCase
import javax.inject.Inject

@HiltViewModel
class PatientHomeViewModel @Inject constructor(
    val defineTimeOfDayUseCase: DefineTimeOfDayUseCase,
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
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getAppointments() {
        GlobalScope.launch {
            runBlocking {
                try {
                    state.appointments =
                        getPatientAppointmentsUseCase.execute(state.patient.id!!).toMutableList()
                } catch (e: Exception) {

                }
            }
        }
    }
}