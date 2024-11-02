package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.disease_history.doctor

sealed class DoctorDiseaseHistoryEvent {
    data object GetDiseasesHistoryEvent : DoctorDiseaseHistoryEvent()

    data object ShowDialogEvent : DoctorDiseaseHistoryEvent()
    data object HideDialogEvent : DoctorDiseaseHistoryEvent()
}