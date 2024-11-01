package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.home.patient

sealed class PatientHomeEvent {
    data object GetAppointmentsEvent : PatientHomeEvent()

    data object ShowDialogEvent : PatientHomeEvent()
    data object HideDialogEvent : PatientHomeEvent()
}