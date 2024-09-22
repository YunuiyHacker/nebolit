package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.sign_in

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.state.ContentState

class SignInState {
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var passwordVisible by mutableStateOf(false)

    var valid by mutableStateOf(false)

    val contentState by mutableStateOf(ContentState())
    var showDialog by mutableStateOf(false)

    var success by mutableStateOf(false)
}