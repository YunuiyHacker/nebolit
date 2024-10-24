package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.doctors.doctors_add

sealed class AdminDoctorsAddEvent {
    data object GetAllSpecializations : AdminDoctorsAddEvent()

    data class ChangeEmailEvent(val email: String) : AdminDoctorsAddEvent()
    data class ChangePasswordEvent(val password: String) : AdminDoctorsAddEvent()

    data class ChangeSurnameEvent(val surname: String) : AdminDoctorsAddEvent()
    data class ChangeNameEvent(val name: String) : AdminDoctorsAddEvent()
    data class ChangeLastnameEvent(val lastname: String) : AdminDoctorsAddEvent()
    data class ChangeAddressOfBirthEvent(val addressOfBirth: String) : AdminDoctorsAddEvent()
    data class ChangeSeriesEvent(val series: String) : AdminDoctorsAddEvent()
    data class ChangeCodeEvent(val code: String) : AdminDoctorsAddEvent()
    data class ChangeSexEvent(val sex: Boolean) : AdminDoctorsAddEvent()
    data class ChangeIssueOrganizationEvent(val issueOrganization: String) : AdminDoctorsAddEvent()
    data class ChangeDepartmentCodeEvent(val departmentCode: String) : AdminDoctorsAddEvent()
    data class ChangeSpecializationIndexEvent(val specializationIndex: Int) : AdminDoctorsAddEvent()
    data class ChangeLicenzeNumberEvent(val licenzeNumber: String) : AdminDoctorsAddEvent()

    data object ShowDateOfBirthPickerDialogEvent : AdminDoctorsAddEvent()
    data object ShowIssueDatePickerDialogEvent : AdminDoctorsAddEvent()
    data object HideDatePickerDialogEvent : AdminDoctorsAddEvent()

    data object OnClickButtonEvent : AdminDoctorsAddEvent()

    data object ShowDialogEvent : AdminDoctorsAddEvent()
    data object HideDialogEvent : AdminDoctorsAddEvent()
}