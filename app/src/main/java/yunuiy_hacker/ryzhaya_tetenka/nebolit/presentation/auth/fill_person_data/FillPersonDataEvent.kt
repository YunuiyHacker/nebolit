package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.fill_person_data

sealed class FillPersonDataEvent {
    data class ChangeSurnameEvent(val surname: String) : FillPersonDataEvent()
    data class ChangeNameEvent(val name: String) : FillPersonDataEvent()
    data class ChangeLastnameEvent(val lastname: String) : FillPersonDataEvent()
    data class ChangeAddressOfBirthEvent(val addressOfBirth: String) : FillPersonDataEvent()
    data class ChangeSeriesEvent(val series: String) : FillPersonDataEvent()
    data class ChangeCodeEvent(val code: String) : FillPersonDataEvent()
    data class ChangeSexEvent(val sex: Boolean) : FillPersonDataEvent()
    data class ChangeIssueOrganizationEvent(val issueOrganization: String) : FillPersonDataEvent()
    data class ChangeDepartmentCodeEvent(val departmentCode: String) : FillPersonDataEvent()

    data class ChangeRegistrationAddressEvent(val registrationAddress: String) : FillPersonDataEvent()
    data class ChangeLiveAddressEvent(val liveAddress: String) : FillPersonDataEvent()
    data class ChangePolicyEvent(val policy: String) : FillPersonDataEvent()
    data class ChangeInsuranceCompanyEvent(val insuranceCompany: String) : FillPersonDataEvent()
    data class ChangeHeightEvent(val height: Int) : FillPersonDataEvent()
    data class ChangeWeightEvent(val weight: Float) : FillPersonDataEvent()

    data object ShowDateOfBirthPickerDialogEvent : FillPersonDataEvent()
    data object ShowIssueDatePickerDialogEvent : FillPersonDataEvent()
    data object HideDatePickerDialogEvent : FillPersonDataEvent()

    data object OnClickButtonEvent : FillPersonDataEvent()

    data object ShowDialogEvent : FillPersonDataEvent()
    data object HideDialogEvent : FillPersonDataEvent()
}