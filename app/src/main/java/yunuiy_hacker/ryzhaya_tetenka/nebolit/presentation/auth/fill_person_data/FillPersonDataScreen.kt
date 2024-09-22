package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.fill_person_data

import android.widget.Button
import android.widget.ToggleButton
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.BUTTON_CORNER_RADIUS
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.Primary
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FillPersonDataScreen(
    viewModel: FillPersonDataViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val dateInteractionSource = remember {
        MutableInteractionSource()
    }

    Scaffold(containerColor = MaterialTheme.colorScheme.onBackground, topBar = {
        TopAppBar(title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = -24.dp),
                text = "Заполнение персональных данных",
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack(
                    route = Route.SignUpScreen.route,
                    inclusive = true,
                    saveState = true
                )
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = if (isSystemInDarkTheme()) Color(
                0xFF131313
            ) else Color(0xFFF9F9F9)
        )
        )
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Чтобы продолжить дальше, Вам необходимо заполнить следующие данные о себе",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        viewModel.onEvent(FillPersonDataEvent.ShowDatePickerDialog)
                    },
                interactionSource = dateInteractionSource,
                value = if (viewModel.state.dateOfBirth.selectedDateMillis != null) SimpleDateFormat(
                    "dd.MM.yyyy"
                ).format(
                    Date(viewModel.state.dateOfBirth.selectedDateMillis!!)
                )
                else "не выбрана",
                onValueChange = {},
                label = {
                    Text(text = "Дата рождения")
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Ваш пол",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(16.dp))
                Row {
                    OutlinedButton(
                        onClick = { viewModel.onEvent(FillPersonDataEvent.ChangeSex(true)) },
                        shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (viewModel.state.sex) Primary else Color.Transparent,
                            contentColor = if (viewModel.state.sex) Color.White else Color.Unspecified
                        ),
                        border = BorderStroke(width = 1.dp, color = Primary)
                    ) {
                        Text(text = "Муж")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedButton(
                        onClick = { viewModel.onEvent(FillPersonDataEvent.ChangeSex(false)) },
                        shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (!viewModel.state.sex) Primary else Color.Transparent,
                            contentColor = if (!viewModel.state.sex) Color.White else Color.Unspecified
                        ),
                        border = BorderStroke(width = 1.dp, color = Primary)
                    ) {
                        Text(text = "Жен")
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.state.registrationAddress,
                onValueChange = {
                    viewModel.onEvent(FillPersonDataEvent.ChangeRegistrationAddress(it.take(300)))
                },
                label = {
                    Text(text = "Адрес регистрации")
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.state.liveAddress,
                onValueChange = {
                    viewModel.onEvent(FillPersonDataEvent.ChangeLiveAddress(it.take(300)))
                },
                label = {
                    Text(text = "Фактический адрес")
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.state.policy,
                onValueChange = {
                    viewModel.onEvent(FillPersonDataEvent.ChangePolicy(it.take(16)))
                },
                label = {
                    Text(text = "Полис")
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.state.insuranceCompany,
                onValueChange = {
                    viewModel.onEvent(FillPersonDataEvent.ChangeInsuranceCompany(it.take(300)))
                },
                label = {
                    Text(text = "Страховая компания")
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.state.height,
                onValueChange = {
                    viewModel.onEvent(FillPersonDataEvent.ChangeHeight(it.take(300)))
                },
                label = {
                    Text(text = "Рост")
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.state.weight,
                onValueChange = {
                    viewModel.onEvent(FillPersonDataEvent.ChangeWeight(it.take(300)))
                },
                label = {
                    Text(text = "Вес")
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { },
                shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
                colors = ButtonDefaults.buttonColors(contentColor = Color.White)
            ) {
                Text(text = "Зарегистрироваться")
            }

            if (dateInteractionSource.collectIsPressedAsState().value) viewModel.onEvent(
                FillPersonDataEvent.ShowDatePickerDialog
            )

            if (viewModel.state.showDatePickerDialog) {
                DatePickerDialog(onDismissRequest = { }, confirmButton = {
                    Button(onClick = { viewModel.onEvent(FillPersonDataEvent.HideDatePickerDialog) }) {
                        Text(text = "Подтвердить", color = Color.White)
                    }
                }) {
                    DatePicker(state = viewModel.state.dateOfBirth)
                }
            }
        }
    }
}