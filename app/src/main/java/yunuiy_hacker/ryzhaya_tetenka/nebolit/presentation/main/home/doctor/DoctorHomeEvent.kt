package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.home.doctor

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DoctorSchedule

sealed class DoctorHomeEvent {
    data object GetDoctorSchedules : DoctorHomeEvent()
    data class SelectDoctorSchedule(val doctorSchedule: DoctorSchedule) : DoctorHomeEvent()

    data object ShowPatientInfoEvent : DoctorHomeEvent()
    data object HidePatientInfoEvent : DoctorHomeEvent()

    data object ShowDialogEvent : DoctorHomeEvent()
    data object HideDialogEvent : DoctorHomeEvent()
}