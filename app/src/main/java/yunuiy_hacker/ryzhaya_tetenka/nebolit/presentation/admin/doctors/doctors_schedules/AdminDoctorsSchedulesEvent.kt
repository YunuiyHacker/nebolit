package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.doctors.doctors_schedules

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DoctorSchedule
import java.util.GregorianCalendar

sealed class AdminDoctorsSchedulesEvent {
    data object GetSchedulesByDoctorAndCabinetsEvent : AdminDoctorsSchedulesEvent()

    data class SelectCurrentScheduleItemEvent(
        val calendar: GregorianCalendar,
        val doctorSchedule: DoctorSchedule
    ) :
        AdminDoctorsSchedulesEvent()

    data class SaveScheduleEvent(
        val hour: Int,
        val minute: Int,
        val cabinetId: Int
    ) : AdminDoctorsSchedulesEvent()

    data object EditScheduleEvent : AdminDoctorsSchedulesEvent()
    data object RemoveScheduleEvent : AdminDoctorsSchedulesEvent()


    data object ShowScheduleDialogEvent : AdminDoctorsSchedulesEvent()
    data object HideScheduleDialogEvent : AdminDoctorsSchedulesEvent()

    data object ShowAppointmentDateDialogEvent : AdminDoctorsSchedulesEvent()
    data object HideAppointmentDateDialogEvent : AdminDoctorsSchedulesEvent()

    data object ShowDialogEvent : AdminDoctorsSchedulesEvent()
    data object HideDialogEvent : AdminDoctorsSchedulesEvent()
}