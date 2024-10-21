package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.patients.patients_list

sealed class AdminPatientsListEvent {
    data class ChangeSelectedPatientIndexEvent(val index: Int) : AdminPatientsListEvent()
    data object DeletePatientEvent : AdminPatientsListEvent()

    data object ShowDialogEvent : AdminPatientsListEvent()
    data object HideDialogEvent : AdminPatientsListEvent()
}