package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.make_appointment.select_specialization

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Specialization

sealed class SelectSpecializationEvent {
    data object GetAllSpecializationsEvent : SelectSpecializationEvent()
    data class SelectCurrentSpecializationEvent(val specialization: Specialization) :
        SelectSpecializationEvent()

    data object ShowDialogEvent : SelectSpecializationEvent()
    data object HideDialogEvent : SelectSpecializationEvent()
}