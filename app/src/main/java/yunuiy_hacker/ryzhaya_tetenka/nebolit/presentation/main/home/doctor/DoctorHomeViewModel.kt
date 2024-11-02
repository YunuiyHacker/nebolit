package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.home.doctor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.common.use_case.GetDoctorSchedulesUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.doctor.use_case.GetPatientInfoUseCase
import java.util.Calendar
import java.util.GregorianCalendar
import javax.inject.Inject

@HiltViewModel
class DoctorHomeViewModel @Inject constructor(
    val dataStoreHelper: DataStoreHelper,
    private val sharedPrefsHelper: SharedPrefsHelper,
    private val getDoctorSchedulesUseCase: GetDoctorSchedulesUseCase,
    private val getPatientInfoUseCase: GetPatientInfoUseCase
) : ViewModel() {
    val state by mutableStateOf(DoctorHomeState())

    fun onEvent(event: DoctorHomeEvent) {
        when (event) {
            is DoctorHomeEvent.GetDoctorSchedules -> getDoctorSchedules()
            is DoctorHomeEvent.SelectDoctorSchedule -> {
                state.doctorSchedule = event.doctorSchedule
            }

            is DoctorHomeEvent.ShowPatientInfoEvent -> getPatientInfo()
            is DoctorHomeEvent.HidePatientInfoEvent -> state.showPatientInfo = false

            is DoctorHomeEvent.ShowDialogEvent -> state.showDialog = true
            is DoctorHomeEvent.HideDialogEvent -> state.showDialog = false
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getDoctorSchedules() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {
                    val startDate = GregorianCalendar()
                    val endDate = GregorianCalendar(
                        startDate.get(Calendar.YEAR),
                        startDate.get(Calendar.MONTH),
                        startDate.get(Calendar.DAY_OF_MONTH) + 14
                    )

                    state.doctorSchedules = getDoctorSchedulesUseCase.execute(
                        doctorId = sharedPrefsHelper.doctor_id,
                        startDate = startDate.time,
                        endDate = endDate.time
                    ).toMutableList()

                    state.contentState.isLoading.value = false
                } catch (e: Exception) {
                    state.contentState.isLoading.value = false
                    state.contentState.exception.value = e
                    state.showDialog = true
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getPatientInfo() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {
                    state.patient =
                        getPatientInfoUseCase.execute(state.doctorSchedule.appointments[0].patientId)
                            ?: Patient()

                    if (state.patient.id != 0) {
                        state.contentState.isLoading.value = false
                        state.showPatientInfo = true
                    } else {
                        throw Exception("При получении данных пациента произошла ошибка")
                    }

                } catch (e: Exception) {
                    state.contentState.isLoading.value = false
                    state.contentState.exception.value = e
                    state.showDialog = true
                }
            }
        }
    }
}