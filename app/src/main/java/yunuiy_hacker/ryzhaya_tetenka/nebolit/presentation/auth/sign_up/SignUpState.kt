package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.state.ContentState

class SignUpState {
    var fullName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var passwordVisible by mutableStateOf(false)
    var confirmPasswordVisible by mutableStateOf(false)

    var policyChecked by mutableStateOf(false)

    var showPolicy by mutableStateOf(false)

    var surname by mutableStateOf("")
    var name by mutableStateOf("")
    var lastname by mutableStateOf("")

    var valid by mutableStateOf(false)

    var contentState by mutableStateOf(ContentState())
    var showDialog by mutableStateOf(false)

    var success by mutableStateOf(false)

    var user by mutableStateOf(User())
}