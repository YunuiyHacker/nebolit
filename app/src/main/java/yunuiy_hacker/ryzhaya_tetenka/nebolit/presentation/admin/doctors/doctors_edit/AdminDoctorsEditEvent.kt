package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.doctors.doctors_edit

sealed class AdminDoctorsEditEvent {
    data object GetAllSpecializations : AdminDoctorsEditEvent()

    data object ValidateEvent: AdminDoctorsEditEvent()

    data class ChangeEmailEvent(val email: String) : AdminDoctorsEditEvent()
    data class ChangePasswordEvent(val password: String) : AdminDoctorsEditEvent()

    data class ChangeSurnameEvent(val surname: String) : AdminDoctorsEditEvent()
    data class ChangeNameEvent(val name: String) : AdminDoctorsEditEvent()
    data class ChangeLastnameEvent(val lastname: String) : AdminDoctorsEditEvent()
    data class ChangeAddressOfBirthEvent(val addressOfBirth: String) : AdminDoctorsEditEvent()
    data class ChangeSeriesEvent(val series: String) : AdminDoctorsEditEvent()
    data class ChangeCodeEvent(val code: String) : AdminDoctorsEditEvent()
    data class ChangeSexEvent(val sex: Boolean) : AdminDoctorsEditEvent()
    data class ChangeIssueOrganizationEvent(val issueOrganization: String) : AdminDoctorsEditEvent()
    data class ChangeDepartmentCodeEvent(val departmentCode: String) : AdminDoctorsEditEvent()
    data class ChangeSpecializationIndexEvent(val specializationIndex: Int): AdminDoctorsEditEvent()
    data class ChangeLicenzeNumberEvent(val licenzeNumber: String): AdminDoctorsEditEvent()

    data object ShowDateOfBirthPickerDialogEvent : AdminDoctorsEditEvent()
    data object ShowIssueDatePickerDialogEvent : AdminDoctorsEditEvent()
    data object HideDatePickerDialogEvent : AdminDoctorsEditEvent()

    data object OnClickButtonEvent : AdminDoctorsEditEvent()

    data object ShowDialogEvent : AdminDoctorsEditEvent()
    data object HideDialogEvent : AdminDoctorsEditEvent()
}