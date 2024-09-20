package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.sign_up

sealed class SignUpEvent() {
    data class ChangeFullNameEvent(val fullName: String) : SignUpEvent()
    data class ChangeEmailEvent(val email: String) : SignUpEvent()
    data class ChangePasswordEvent(val password: String) : SignUpEvent()
    data class ChangeConfirmPasswordEvent(val confirmPassword: String) : SignUpEvent()
    data object TogglePasswordVisibilityEvent : SignUpEvent()
    data object ToggleConfirmPasswordVisibilityEvent : SignUpEvent()

    data object TogglePolicyCheckboxEvent : SignUpEvent()
    data object ShowPolicyEvent : SignUpEvent()
    data object HidePolicyEvent : SignUpEvent()
}