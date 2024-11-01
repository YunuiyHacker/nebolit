package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.doctors.doctors_schedules

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalTime
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DoctorSchedule
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toDateOfMobileFormat
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toLocalDate
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toPassportDate
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case.GetCabinetsUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case.GetAdminDoctorSchedulesUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case.RemoveDoctorScheduleUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case.SaveDoctorScheduleUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case.UpdateDoctorScheduleUseCase
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import javax.inject.Inject

@HiltViewModel
class AdminDoctorsSchedulesViewModel @Inject constructor(
    val dataStoreHelper: DataStoreHelper,
    private val getDoctorSchedulesUseCase: GetAdminDoctorSchedulesUseCase,
    private val saveDoctorScheduleUseCase: SaveDoctorScheduleUseCase,
    private val getCabinetsUseCase: GetCabinetsUseCase,
    private val updateDoctorScheduleUseCase: UpdateDoctorScheduleUseCase,
    private val removeDoctorScheduleUseCase: RemoveDoctorScheduleUseCase
) : ViewModel() {
    val state by mutableStateOf(AdminDoctorsSchedulesState())

    init {
        for (i in 0..15) {
            val calendar = GregorianCalendar()
            state.calendarList.add(calendar)
            calendar.add(Calendar.DAY_OF_MONTH, i)
        }
    }

    fun onEvent(event: AdminDoctorsSchedulesEvent) {
        when (event) {
            is AdminDoctorsSchedulesEvent.GetSchedulesByDoctorAndCabinetsEvent -> getSchedulesByAndCabinetsDoctor()

            is AdminDoctorsSchedulesEvent.SelectCurrentScheduleItemEvent -> {
                state.selectedDoctorSchedule = event.doctorSchedule
                state.selectedCalendar = event.calendar
                state.contentState.exception.value =
                    Exception(event.doctorSchedule.toString() + "_" + event.calendar)
                state.showDialog = true
            }

            is AdminDoctorsSchedulesEvent.ShowDialogEvent -> state.showDialog = true
            is AdminDoctorsSchedulesEvent.HideDialogEvent -> state.showDialog = false
            is AdminDoctorsSchedulesEvent.SaveScheduleEvent -> saveSchedule(
                hour = event.hour, minute = event.minute, cabinetId = event.cabinetId
            )

            is AdminDoctorsSchedulesEvent.EditScheduleEvent -> editSchedule()
            is AdminDoctorsSchedulesEvent.RemoveScheduleEvent -> removeSchedule()

            is AdminDoctorsSchedulesEvent.ShowScheduleDialogEvent -> state.showScheduleDialog = true
            is AdminDoctorsSchedulesEvent.HideScheduleDialogEvent -> state.showScheduleDialog =
                false

            is AdminDoctorsSchedulesEvent.ShowAppointmentDateDialogEvent -> state.showAppointmentDataDialog =
                true

            is AdminDoctorsSchedulesEvent.HideAppointmentDateDialogEvent -> state.showAppointmentDataDialog =
                false
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getSchedulesByAndCabinetsDoctor() {
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
                        state.adminDoctor.id, startDate.time, endDate.time
                    ).toMutableList()
                    state.cabinets = getCabinetsUseCase.execute().toMutableList()
                    state.contentState.isLoading.value = false
                } catch (e: Exception) {
                    state.contentState.exception.value = e
                    state.contentState.isLoading.value = false
                    state.showDialog = true
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun saveSchedule(hour: Int, minute: Int, cabinetId: Int) {
        state.showScheduleDialog = false
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {
                    var doctorSchedule: DoctorSchedule? = null
                    if (!state.scheduleIsEditState) {
                        doctorSchedule = saveDoctorScheduleUseCase.execute(
                            doctorSchedule = DoctorSchedule(
                                doctorId = state.adminDoctor.id,
                                time = LocalTime(hour = hour, minute = minute),
                                date = Date(state.appointmentDate.selectedDateMillis!!).toLocalDate(),
                                cabinetId = cabinetId
                            )
                        )
                    } else {
                        doctorSchedule = updateDoctorScheduleUseCase.execute(
                            doctorSchedule = state.selectedDoctorSchedule.copy(
                                doctorId = state.adminDoctor.id,
                                time = LocalTime(hour = hour, minute = minute),
                                date = Date(state.appointmentDate.selectedDateMillis!!).toLocalDate(),
                                cabinetId = cabinetId
                            )
                        )
                    }
                    if (doctorSchedule != null) {
                        getSchedulesByAndCabinetsDoctor()
                    } else {
                        throw Exception("При сохранении расписания возникла ошибка")
                    }

                    state.contentState.isLoading.value = false
                } catch (e: Exception) {
                    state.contentState.exception.value = e
                    state.showDialog = true
                    state.contentState.isLoading.value = false
                }
            }
        }
    }

    private fun editSchedule() {
        state.appointmentDate.selectedDateMillis =
            state.selectedDoctorSchedule.date.toPassportDate()
                .toDateOfMobileFormat().time + (1000 * 60 * 60 * 24)
        state.selectedCabinet =
            state.cabinets.find { state.selectedDoctorSchedule.cabinetId == it.id }!!

        state.scheduleIsEditState = true

        state.showScheduleDialog = true
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun removeSchedule() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {
                    removeDoctorScheduleUseCase.execute(state.selectedDoctorSchedule)

                    getSchedulesByAndCabinetsDoctor()

                    throw Exception("Запись успешно удалена")
                } catch (e: Exception) {
                    state.contentState.exception.value = e
                    state.showDialog = true
                    state.contentState.isLoading.value = false
                }
            }
        }
    }

}