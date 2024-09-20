package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.sign_up.SignUpEvent
import yunuiy_hacker.ryzhaya_tetenka.nebolit.utils.Constants.PATTERN_EMAIL
import javax.inject.Inject

@HiltViewModel
class SIgnInViewModel @Inject constructor() : ViewModel() {
    val state by mutableStateOf(SignInState())

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.ChangeEmailEvent -> {
                state.email = event.email
                validate()
            }
            is SignInEvent.ChangePasswordEvent -> {
                state.password = event.password
                validate()
            }
            is SignInEvent.TogglePasswordVisibilityEvent -> state.passwordVisible =
                !state.passwordVisible
        }
    }

    fun validate() {
        if (PATTERN_EMAIL.matcher(state.email).matches())
            if (state.password.length >= 8)
                state.valid = true
            else state.valid = false
        else state.valid = false
    }
}