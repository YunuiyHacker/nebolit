package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.profile.doctor

sealed class DoctorProfileEvent {
    data object ShowPassportEvent : DoctorProfileEvent()
    data object HidePassportEvent : DoctorProfileEvent()

    data object ShowSettingsEvent : DoctorProfileEvent()
    data object HideSettingsEvent : DoctorProfileEvent()

    data class SetThemeEvent(val isDarkTheme: Boolean) : DoctorProfileEvent()
    data object ExitEvent : DoctorProfileEvent()
}