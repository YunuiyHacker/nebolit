package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.main

sealed class AdminMainEvent {
    data object ShowSettingsEvent : AdminMainEvent()
    data object HideSettingsEvent : AdminMainEvent()

    data class SetThemeEvent(val isDarkTheme: Boolean) : AdminMainEvent()
    data object ExitEvent : AdminMainEvent()
}