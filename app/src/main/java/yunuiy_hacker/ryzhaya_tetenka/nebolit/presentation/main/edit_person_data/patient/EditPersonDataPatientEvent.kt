package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.edit_person_data.patient

sealed class EditPersonDataPatientEvent {
    data class ChangeSurnameEvent(val surname: String) : EditPersonDataPatientEvent()
    data class ChangeNameEvent(val name: String) : EditPersonDataPatientEvent()
    data class ChangeLastnameEvent(val lastname: String) : EditPersonDataPatientEvent()

    data class ChangeRegistrationAddressEvent(val registrationAddress: String) : EditPersonDataPatientEvent()
    data class ChangeLiveAddressEvent(val liveAddress: String) : EditPersonDataPatientEvent()
    data class ChangePolicyEvent(val policy: String) : EditPersonDataPatientEvent()
    data class ChangeInsuranceCompanyEvent(val insuranceCompany: String) : EditPersonDataPatientEvent()
    data class ChangeHeightEvent(val height: Int) : EditPersonDataPatientEvent()
    data class ChangeWeightEvent(val weight: Float) : EditPersonDataPatientEvent()

    data object ShowDateOfBirthPickerDialogEvent : EditPersonDataPatientEvent()
    data object ShowIssueDatePickerDialogEvent : EditPersonDataPatientEvent()
    data object HideDatePickerDialogEvent : EditPersonDataPatientEvent()

    data object OnClickButtonEvent : EditPersonDataPatientEvent()

    data object ShowDialogEvent : EditPersonDataPatientEvent()
    data object HideDialogEvent : EditPersonDataPatientEvent()
}