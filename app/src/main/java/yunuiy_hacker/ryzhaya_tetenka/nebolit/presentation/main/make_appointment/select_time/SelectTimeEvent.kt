package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.make_appointment.select_time

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Specialization

sealed class SelectTimeEvent {
    data object GetAllSpecializationsEvent : SelectTimeEvent()
    data class SelectCurrentSpecializationEvent(val specialization: Specialization) :
        SelectTimeEvent()

    data object ShowDialogEvent : SelectTimeEvent()
    data object HideDialogEvent : SelectTimeEvent()
}