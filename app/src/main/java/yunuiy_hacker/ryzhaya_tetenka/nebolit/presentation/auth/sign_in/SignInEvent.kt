package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.sign_in

sealed class SignInEvent {
    data class ChangeEmailEvent(val email: String) : SignInEvent()
    data class ChangePasswordEvent(val password: String) : SignInEvent()

    data object TogglePasswordVisibilityEvent : SignInEvent()

    data object OnClickButtonEvent : SignInEvent()

    data object ShowDialogEvent : SignInEvent()
    data object HideDialogEvent : SignInEvent()
}