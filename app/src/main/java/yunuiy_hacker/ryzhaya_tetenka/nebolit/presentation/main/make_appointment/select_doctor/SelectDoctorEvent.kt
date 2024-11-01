package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.make_appointment.select_doctor

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Doctor

sealed class SelectDoctorEvent {
    data object GetDoctorsBySpecializationEvent : SelectDoctorEvent()
    data class SelectCurrentDoctorEvent(val doctor: Doctor) :
        SelectDoctorEvent()

    data object ShowDialogEvent : SelectDoctorEvent()
    data object HideDialogEvent : SelectDoctorEvent()
}