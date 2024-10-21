package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.sign_in

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import yunuiy_hacker.ryzhaya_tetenka.nebolit.R
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.ContentDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.LoadingDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.BUTTON_CORNER_RADIUS

@Composable
fun SignInScreen(navController: NavHostController, viewModel: SignInViewModel = hiltViewModel()) {
    Scaffold(Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onBackground,
        bottomBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Еще нет аккаунта?",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    ClickableText(text = buildAnnotatedString { append("Зарегистрироваться") },
                        style = TextStyle(color = MaterialTheme.colorScheme.primary),
                        onClick = {
                            navController.navigate(Route.SignUpScreen.route)
                        })
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(77.dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.state.email,
                onValueChange = {
                    viewModel.onEvent(SignInEvent.ChangeEmailEvent(it.take(50)))
                },
                label = {
                    Text(text = "E-mail")
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                )
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.state.password,
                onValueChange = {
                    viewModel.onEvent(SignInEvent.ChangePasswordEvent(it.take(50)))
                },
                label = {
                    Text(text = "Пароль")
                },
                trailingIcon = {
                    IconButton(onClick = { viewModel.onEvent(SignInEvent.TogglePasswordVisibilityEvent) }) {
                        Icon(
                            imageVector = if (viewModel.state.passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = ""
                        )
                    }
                },
                visualTransformation = if (viewModel.state.passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                )
            )
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.onEvent(SignInEvent.OnClickButtonEvent)
                },
                shape = RoundedCornerShape(
                    BUTTON_CORNER_RADIUS
                ),
                enabled = viewModel.state.valid,
                colors = ButtonDefaults.buttonColors(contentColor = Color.White)
            ) {
                Text(text = "Войти")
            }
        }

        if (viewModel.state.contentState.isLoading.value) {
            LoadingDialog(onDismissRequest = {})
        }

        if (viewModel.state.showDialog)
            ContentDialog(
                text = if (viewModel.state.contentState.data.value == null) viewModel.state.contentState.exception.value?.message.toString() else viewModel.state.contentState.data.value.toString(),
                onDismissRequest = { viewModel.onEvent(SignInEvent.HideDialogEvent) })

        if (viewModel.state.success) {
            navController.currentBackStackEntry?.savedStateHandle?.set("user", viewModel.state.user)
            if (viewModel.state.isAdmin)
                navController.navigate(Route.AdminMainScreen.route)
            else
                navController.navigate(Route.HomeScreen.route)
        }
    }
}