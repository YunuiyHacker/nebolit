package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.fill_person_data

sealed class FillPersonDataEvent {
    data class ChangeSex(val sex: Boolean) : FillPersonDataEvent()
    data class ChangeRegistrationAddress(val registrationAddress: String) : FillPersonDataEvent()
    data class ChangeLiveAddress(val liveAddress: String) : FillPersonDataEvent()
    data class ChangePolicy(val policy: String) : FillPersonDataEvent()
    data class ChangeInsuranceCompany(val insuranceCompany: String) : FillPersonDataEvent()
    data class ChangeWeight(val weight: String) : FillPersonDataEvent()
    data class ChangeHeight(val height: String) : FillPersonDataEvent()

    data object ShowDatePickerDialog : FillPersonDataEvent()
    data object HideDatePickerDialog : FillPersonDataEvent()
}