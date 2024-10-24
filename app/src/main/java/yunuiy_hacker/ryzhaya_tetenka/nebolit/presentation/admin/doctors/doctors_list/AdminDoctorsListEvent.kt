package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.doctors.doctors_list

sealed class AdminDoctorsListEvent {
    data class ChangeSelectedDoctorIndexEvent(val index: Int) : AdminDoctorsListEvent()
    data object DeleteDoctorEvent : AdminDoctorsListEvent()

    data object ShowDialogEvent : AdminDoctorsListEvent()
    data object HideDialogEvent : AdminDoctorsListEvent()
}