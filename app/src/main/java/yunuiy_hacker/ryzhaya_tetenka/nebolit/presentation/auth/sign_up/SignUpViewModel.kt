package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.isEmail
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.SignUpModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SaveReadPersonDataUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SignUpUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.utils.Constants.PATTERN_EMAIL
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    val dataStoreHelper: DataStoreHelper,
    private val signUpUseCase: SignUpUseCase,
    private val saveReadPersonDataUseCase: SaveReadPersonDataUseCase
) : ViewModel() {
    val state by mutableStateOf(SignUpState())

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.ChangeEmailEvent -> {
                state.email = event.email
                validate()
            }

            is SignUpEvent.ChangePasswordEvent -> {
                state.password = event.password
                validate()
            }

            is SignUpEvent.ChangeConfirmPasswordEvent -> {
                state.confirmPassword = event.confirmPassword
                validate()
            }

            is SignUpEvent.TogglePasswordVisibilityEvent -> state.passwordVisible =
                !state.passwordVisible

            is SignUpEvent.ToggleConfirmPasswordVisibilityEvent -> state.confirmPasswordVisible =
                !state.confirmPasswordVisible

            is SignUpEvent.TogglePolicyCheckboxEvent -> {
                state.policyChecked = !state.policyChecked
                validate()
            }

            is SignUpEvent.ShowPolicyEvent -> state.showPolicy = true

            is SignUpEvent.HidePolicyEvent -> state.showPolicy = false

            is SignUpEvent.OnClickButton -> registration()

            is SignUpEvent.ShowDialog -> state.showDialog = true
            is SignUpEvent.HideDialog -> state.showDialog = false
        }
    }

    fun validate() {
        if (state.email.isEmail())
            if (state.password.length >= 8) if (state.password == state.confirmPassword) if (state.policyChecked) {
                state.valid = true
            } else state.valid = false
            else state.valid = false
            else state.valid = false
        else state.valid = false
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun registration() {
        GlobalScope.launch {
            runBlocking {
                try {
                    state.contentState.isLoading.value = true

                    val signUpModel: SignUpModel = SignUpModel(
                        email = state.email,
                        password = state.password
                    )

                    if (signUpUseCase.checkRegistrationByEmail.invoke(signUpModel)) {
                        val user: User? = signUpUseCase.registrationUser(
                            signUpModel = signUpModel
                        )
                        if (user == null) throw Exception("Не удалось создать пользователя")
                        else {
                            state.user = user
                            saveReadPersonDataUseCase.saveUser.invoke(user)
                            state.success = true
                        }
                    } else throw Exception("Пользователь с такой почтой уже зарегистрирован")
                } catch (e: Exception) {
                    state.contentState.isLoading.value = false
                    state.contentState.exception.value = e
                    state.showDialog = true
                }
            }
        }
    }
}