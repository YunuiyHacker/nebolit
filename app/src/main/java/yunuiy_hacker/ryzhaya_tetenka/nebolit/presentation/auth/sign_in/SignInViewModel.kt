package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.enums.Role
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Doctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.parent.RoleObject
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.LoadRoleObjectModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.SignInModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SaveReadPersonDataUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SignInUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.utils.Constants.PATTERN_EMAIL
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val saveReadPersonDataUseCase: SaveReadPersonDataUseCase
) : ViewModel() {
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

            is SignInEvent.OnClickButtonEvent -> logIn()

            is SignInEvent.ShowDialogEvent -> state.showDialog = true
            is SignInEvent.HideDialogEvent -> state.showDialog = false
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

        var user: User?

        GlobalScope.launch {
            try {
                runBlocking {
                    user = signInUseCase.logIn.invoke(
                        signInModel = SignInModel(
                            email = state.email, password = state.password
                        )
                    )
                    if (user == null) {
                        state.contentState.exception.value
                        throw Exception(
                            "Пользователя с такими данными не существует"
                        )
                        state.showDialog = true
                    } else {
                        val passport = signInUseCase.getPassportById.invoke(user!!.passportId!!)
                        if (passport == null) {
                            state.contentState.exception.value
                            throw Exception(
                                "К сожалению, мы не нашли в базе ваш паспорт, а без него войти нельзя"
                            )
                            state.showDialog = true
                        } else {
                            saveReadPersonDataUseCase.savePassport.invoke(passport!!)
                            saveReadPersonDataUseCase.saveUser.invoke(user!!)

                            val role: Role = signInUseCase.defineRole.invoke(user_id = user!!.id!!)

                            val roleObject: RoleObject? = signInUseCase.loadRoleObject.invoke(
                                loadRoleObjectModel = LoadRoleObjectModel(
                                    user_id = user!!.id!!,
                                    role = role
                                )
                            )

                            if (roleObject is Patient)
                                saveReadPersonDataUseCase.savePatient(roleObject as Patient)
                            else if (roleObject is Doctor)
                                saveReadPersonDataUseCase.saveDoctor(roleObject as Doctor)

                            state.success = true
                        }
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