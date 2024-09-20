package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.utils.Constants.PATTERN_EMAIL
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val supabaseClient: SupabaseClient) :
    ViewModel() {
    val state by mutableStateOf(SignUpState())

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.ChangeFullNameEvent -> {
                state.fullName = event.fullName
                validate()
            }

            is SignUpEvent.ChangeEmailEvent -> {
                state.email = event.email
                validate()
            }

            is SignUpEvent.ChangePasswordEvent -> {
                state.password = event.password
                validate()
            }

            is SignUpEvent.ChangeConfirmPasswordEvent -> {
                state.confirmPassword =
                    event.confirmPassword
                validate()
            }

            is SignUpEvent.TogglePasswordVisibilityEvent -> state.passwordVisible =
                !state.passwordVisible

            is SignUpEvent.ToggleConfirmPasswordVisibilityEvent -> state.confirmPasswordVisible =
                !state.confirmPasswordVisible

            is SignUpEvent.TogglePolicyCheckboxEvent -> {
                state.policyChecked =
                    !state.policyChecked
                validate()
            }

            is SignUpEvent.ShowPolicyEvent -> state.showPolicy =
                true

            is SignUpEvent.HidePolicyEvent -> state.showPolicy =
                false
        }
    }

    fun validate() {
        val fullNameSplit = state.fullName.split(" ")

        if (fullNameSplit.size == 3)
            if (PATTERN_EMAIL.matcher(state.email).matches())
                if (state.password.length >= 8)
                    if (state.password == state.confirmPassword)
                        if (state.policyChecked) {
                            state.valid = true
                            state.surname = fullNameSplit[0]
                            state.name = fullNameSplit[1]
                            state.lastname = fullNameSplit[2]
                        } else
                            state.valid = false
                    else
                        state.valid = false
                else
                    state.valid = false
            else
                state.valid = false
        else
            state.valid = false
    }
}