package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.disease_history

sealed class DiseaseHistoryEvent {
    data object GetDiseasesHistoryEvent : DiseaseHistoryEvent()

    data object ShowDialogEvent : DiseaseHistoryEvent()
    data object HideDialogEvent : DiseaseHistoryEvent()
}