package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.net.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.SignInModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SignInUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.utils.Constants.PATTERN_EMAIL
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val signInUseCase: SignInUseCase) : ViewModel() {
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

            is SignInEvent.OnClickButton -> logIn()

            is SignInEvent.ShowDialog -> state.showDialog = true
            is SignInEvent.HideDialog -> state.showDialog = false
        }
    }

    private fun validate() {
        if (PATTERN_EMAIL.matcher(state.email)
                .matches()
        ) if (state.password.length >= 8) state.valid = true
        else state.valid = false
        else state.valid = false
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun logIn() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            try {
                runBlocking {
                    val user: User? = signInUseCase.execute(
                        signInModel = SignInModel(
                            email = state.email, password = state.password
                        )
                    )
                    if (user == null) {
                        state.contentState.data.value =
                            "Пользователя с такими данными не существует"
                        state.showDialog = true
                    } else {
                        state.success = true
                    }
                    state.contentState.isLoading.value = false
                }
            } catch (e: Exception) {
                state.contentState.exception.value = e
                state.contentState.isLoading.value = false
                state.showDialog = true
            }
        }
    }
}