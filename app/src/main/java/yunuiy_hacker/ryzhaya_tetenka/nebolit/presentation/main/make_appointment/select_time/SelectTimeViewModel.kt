package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.make_appointment.select_time

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Appointment
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.common.use_case.GetDoctorSchedulesUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.common.use_case.MakeAppointmentUseCase
import java.util.Calendar
import java.util.GregorianCalendar
import javax.inject.Inject

@HiltViewModel
class SelectTimeViewModel @Inject constructor(
    val dataStoreHelper: DataStoreHelper,
    private val sharedPrefsHelper: SharedPrefsHelper,
    private val getDoctorSchedulesUseCase: GetDoctorSchedulesUseCase,
    private val makeAppointmentUseCase: MakeAppointmentUseCase
) : ViewModel() {
    val state by mutableStateOf(SelectTimeState())

    fun onEvent(event: SelectTimeEvent) {
        when (event) {
            is SelectTimeEvent.GetDoctorSchedules -> getDoctorSchedules()
            is SelectTimeEvent.SelectDoctorSchedule -> {
                state.doctorSchedule = event.doctorSchedule
            }

            is SelectTimeEvent.MakeAppointmentEvent -> makeAppointment()

            is SelectTimeEvent.ShowMakeAppointmentEvent -> state.showMakeAppointment = true
            is SelectTimeEvent.HideMakeAppointmentEvent -> state.showMakeAppointment = false

            is SelectTimeEvent.ShowDialogEvent -> state.showDialog = true
            is SelectTimeEvent.HideDialogEvent -> state.showDialog = false
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
                        doctorId = state.doctorId,
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

    private fun makeAppointment() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {

                    val appointment = makeAppointmentUseCase.execute(
                        appointment = Appointment(
                            patientId = sharedPrefsHelper.patient_id,
                            doctorScheduleId = state.doctorSchedule.id!!,
                            appointmentStatusId = 1
                        )
                    )

                    getDoctorSchedules()

                    state.contentState.isLoading.value = false

                    if (appointment != null) {
                        state.showMakeAppointment = false
                        throw Exception("Вы успешно записаны")
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