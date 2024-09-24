package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.savedstate.SavedStateRegistry
import yunuiy_hacker.ryzhaya_tetenka.nebolit.R
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.dialog.ContentDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.dialog.LoadingDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.BUTTON_CORNER_RADIUS

@Composable
fun SignUpScreen(
    navController: NavHostController, viewModel: SignUpViewModel = hiltViewModel()
) {
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
                        text = "Уже есть аккаунт?",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    ClickableText(text = buildAnnotatedString { append("Войти") },
                        style = TextStyle(color = MaterialTheme.colorScheme.primary),
                        onClick = {
                            navController.popBackStack()
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
                value = viewModel.state.fullName,
                onValueChange = {
                    viewModel.onEvent(SignUpEvent.ChangeFullNameEvent(it.take(152)))
                },
                label = {
                    Text(text = "ФИО")
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                )
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.state.email,
                onValueChange = {
                    viewModel.onEvent(SignUpEvent.ChangeEmailEvent(it.take(50)))
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
                    viewModel.onEvent(SignUpEvent.ChangePasswordEvent(it.take(50)))
                },
                label = {
                    Text(text = "Пароль")
                },
                trailingIcon = {
                    IconButton(onClick = { viewModel.onEvent(SignUpEvent.TogglePasswordVisibilityEvent) }) {
                        Icon(
                            imageVector = if (viewModel.state.passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = ""
                        )
                    }
                },
                visualTransformation = if (viewModel.state.passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
                )
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.state.confirmPassword,
                onValueChange = {
                    viewModel.onEvent(SignUpEvent.ChangeConfirmPasswordEvent(it.take(50)))
                },
                label = {
                    Text(text = "Потдверждение пароля")
                },
                trailingIcon = {
                    IconButton(onClick = { viewModel.onEvent(SignUpEvent.ToggleConfirmPasswordVisibilityEvent) }) {
                        Icon(
                            imageVector = if (viewModel.state.confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = ""
                        )
                    }
                },
                visualTransformation = if (viewModel.state.confirmPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    modifier = Modifier.offset(x = -14.dp),
                    checked = viewModel.state.policyChecked,
                    onCheckedChange = {
                        viewModel.onEvent(SignUpEvent.TogglePolicyCheckboxEvent)
                    },
                    colors = CheckboxDefaults.colors(checkmarkColor = Color.White)
                )
                Spacer(modifier = Modifier.width(8.dp))
                ClickableText(modifier = Modifier.offset(x = -14.dp), text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
                        append("Я ознакомился с ")
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            append("правилами предоставления услуг")
                        }
                        append(" и принимаю их")
                    }
                }, onClick = {
                    viewModel.onEvent(SignUpEvent.ShowPolicyEvent)
                })
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.onEvent(SignUpEvent.OnClickButton)
                },
                shape = RoundedCornerShape(
                    BUTTON_CORNER_RADIUS
                ),
                enabled = viewModel.state.valid,
                colors = ButtonDefaults.buttonColors(contentColor = Color.White)
            ) {
                Text(text = "Продолжить")
            }
        }

        if (viewModel.state.showPolicy) {
            Dialog(onDismissRequest = { viewModel.onEvent(SignUpEvent.HidePolicyEvent) }) {
                Column(
                    modifier = Modifier.background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(16.dp)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Правила предоставления услуг",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

        if (viewModel.state.contentState.isLoading.value) LoadingDialog(onDismissRequest = {
            viewModel.onEvent(SignUpEvent.HideDialog)
        })

        if (viewModel.state.showDialog) ContentDialog(text = if (viewModel.state.contentState.exception.value != null) viewModel.state.contentState.exception.value?.message.toString() else viewModel.state.contentState.data.value.toString(),
            onDismissRequest = {
                viewModel.onEvent(SignUpEvent.HideDialog)
            })

        if (viewModel.state.success) {
            navController.navigate(Route.FillPersonDataScreen.withIntArguments(viewModel.state.user.id!!))
        }
    }
}