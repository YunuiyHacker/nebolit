package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.make_appointment.select_time

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DoctorSchedule

sealed class SelectTimeEvent {
    data object GetDoctorSchedules : SelectTimeEvent()
    data class SelectDoctorSchedule(val doctorSchedule: DoctorSchedule) :
        SelectTimeEvent()

    data object MakeAppointmentEvent : SelectTimeEvent()

    data object ShowMakeAppointmentEvent : SelectTimeEvent()
    data object HideMakeAppointmentEvent : SelectTimeEvent()

    data object ShowDialogEvent : SelectTimeEvent()
    data object HideDialogEvent : SelectTimeEvent()
}