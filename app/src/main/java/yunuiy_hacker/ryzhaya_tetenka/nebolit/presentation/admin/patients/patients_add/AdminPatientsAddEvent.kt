package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.patients.patients_add

sealed class AdminPatientsAddEvent {
    data class ChangeEmailEvent(val email: String) : AdminPatientsAddEvent()
    data class ChangePasswordEvent(val password: String) : AdminPatientsAddEvent()
    
    data class ChangeSurnameEvent(val surname: String) : AdminPatientsAddEvent()
    data class ChangeNameEvent(val name: String) : AdminPatientsAddEvent()
    data class ChangeLastnameEvent(val lastname: String) : AdminPatientsAddEvent()
    data class ChangeAddressOfBirthEvent(val addressOfBirth: String) : AdminPatientsAddEvent()
    data class ChangeSeriesEvent(val series: String) : AdminPatientsAddEvent()
    data class ChangeCodeEvent(val code: String) : AdminPatientsAddEvent()
    data class ChangeSexEvent(val sex: Boolean) : AdminPatientsAddEvent()
    data class ChangeIssueOrganizationEvent(val issueOrganization: String) : AdminPatientsAddEvent()
    data class ChangeDepartmentCodeEvent(val departmentCode: String) : AdminPatientsAddEvent()

    data class ChangeRegistrationAddressEvent(val registrationAddress: String) : AdminPatientsAddEvent()
    data class ChangeLiveAddressEvent(val liveAddress: String) : AdminPatientsAddEvent()
    data class ChangePolicyEvent(val policy: String) : AdminPatientsAddEvent()
    data class ChangeInsuranceCompanyEvent(val insuranceCompany: String) : AdminPatientsAddEvent()
    data class ChangeHeightEvent(val height: Int) : AdminPatientsAddEvent()
    data class ChangeWeightEvent(val weight: Float) : AdminPatientsAddEvent()

    data object ShowDateOfBirthPickerDialogEvent : AdminPatientsAddEvent()
    data object ShowIssueDatePickerDialogEvent : AdminPatientsAddEvent()
    data object HideDatePickerDialogEvent : AdminPatientsAddEvent()

    data object OnClickButtonEvent : AdminPatientsAddEvent()

    data object ShowDialogEvent : AdminPatientsAddEvent()
    data object HideDialogEvent : AdminPatientsAddEvent()
}