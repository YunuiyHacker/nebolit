package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.patients.patients_edit

sealed class AdminPatientsEditEvent {
    data object ValidateEvent: AdminPatientsEditEvent()

    data class ChangeEmailEvent(val email: String) : AdminPatientsEditEvent()
    data class ChangePasswordEvent(val password: String) : AdminPatientsEditEvent()

    data class ChangeSurnameEvent(val surname: String) : AdminPatientsEditEvent()
    data class ChangeNameEvent(val name: String) : AdminPatientsEditEvent()
    data class ChangeLastnameEvent(val lastname: String) : AdminPatientsEditEvent()
    data class ChangeAddressOfBirthEvent(val addressOfBirth: String) : AdminPatientsEditEvent()
    data class ChangeSeriesEvent(val series: String) : AdminPatientsEditEvent()
    data class ChangeCodeEvent(val code: String) : AdminPatientsEditEvent()
    data class ChangeSexEvent(val sex: Boolean) : AdminPatientsEditEvent()
    data class ChangeIssueOrganizationEvent(val issueOrganization: String) : AdminPatientsEditEvent()
    data class ChangeDepartmentCodeEvent(val departmentCode: String) : AdminPatientsEditEvent()

    data class ChangeRegistrationAddressEvent(val registrationAddress: String) : AdminPatientsEditEvent()
    data class ChangeLiveAddressEvent(val liveAddress: String) : AdminPatientsEditEvent()
    data class ChangePolicyEvent(val policy: String) : AdminPatientsEditEvent()
    data class ChangeInsuranceCompanyEvent(val insuranceCompany: String) : AdminPatientsEditEvent()
    data class ChangeHeightEvent(val height: Int) : AdminPatientsEditEvent()
    data class ChangeWeightEvent(val weight: Float) : AdminPatientsEditEvent()

    data object ShowDateOfBirthPickerDialogEvent : AdminPatientsEditEvent()
    data object ShowIssueDatePickerDialogEvent : AdminPatientsEditEvent()
    data object HideDatePickerDialogEvent : AdminPatientsEditEvent()

    data object OnClickButtonEvent : AdminPatientsEditEvent()

    data object ShowDialogEvent : AdminPatientsEditEvent()
    data object HideDialogEvent : AdminPatientsEditEvent()
}