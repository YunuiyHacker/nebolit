package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.disease_history.patient

sealed class PatientDiseaseHistoryEvent {
    data object GetDiseasesHistoryEvent : PatientDiseaseHistoryEvent()

    data object ShowDialogEvent : PatientDiseaseHistoryEvent()
    data object HideDialogEvent : PatientDiseaseHistoryEvent()
}