package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.profile.patient

sealed class PatientProfileEvent {
    data object ShowPassportEvent : PatientProfileEvent()
    data object HidePassportEvent : PatientProfileEvent()
    data object MoreInformationVisibilityToggleEvent : PatientProfileEvent()

    data object ShowSettingsEvent : PatientProfileEvent()
    data object HideSettingsEvent : PatientProfileEvent()

    data class SetThemeEvent(val isDarkTheme: Boolean) : PatientProfileEvent()
    data object ExitEvent : PatientProfileEvent()
}