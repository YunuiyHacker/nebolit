package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.sign_in

import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.sign_up.SignUpEvent

sealed class SignInEvent {
    data class ChangeEmailEvent(val email: String) : SignInEvent()
    data class ChangePasswordEvent(val password: String) : SignInEvent()

    data object TogglePasswordVisibilityEvent : SignInEvent()
}