package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.auth.fill_person_data

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.isDepartmentCode
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.isNumeric
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.ContentDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.LoadingDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.BUTTON_CORNER_RADIUS
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.Primary
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FillPersonDataScreen(
    viewModel: FillPersonDataViewModel = hiltViewModel(), navController: NavHostController
) {
    val isDarkTheme = viewModel.dataStoreHelper.getTheme().collectAsState(initial = false).value

    val dateOfBirthInteractionSource = remember {
        MutableInteractionSource()
    }
    val issueDateInteractionSource = remember {
        MutableInteractionSource()
    }

    viewModel.state.let { state ->
        Scaffold(containerColor = MaterialTheme.colorScheme.onBackground, topBar = {
            TopAppBar(title = {
                Column {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(x = -24.dp),
                        text = "Заполнение персональных данных",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }, navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack(
                        route = Route.SignUpScreen.route, inclusive = true, saveState = true
                    )
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = if (isDarkTheme) Color(
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
                Text(
                    text = "Паспортные данные",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.surname,
                    onValueChange = {
                        viewModel.onEvent(FillPersonDataEvent.ChangeSurnameEvent(it.take(100)))
                    },
                    label = {
                        Text(text = "Фамилия")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.name,
                    onValueChange = {
                        viewModel.onEvent(FillPersonDataEvent.ChangeNameEvent(it.take(100)))
                    },
                    label = {
                        Text(text = "Имя")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.lastname,
                    onValueChange = {
                        viewModel.onEvent(FillPersonDataEvent.ChangeLastnameEvent(it.take(100)))
                    },
                    label = {
                        Text(text = "Отчество")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    interactionSource = dateOfBirthInteractionSource,
                    value = if (state.dateOfBirth.selectedDateMillis != null) SimpleDateFormat(
                        "dd.MM.yyyy"
                    ).format(
                        Date(state.dateOfBirth.selectedDateMillis!!)
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
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.addressOfBirth,
                    onValueChange = {
                        viewModel.onEvent(FillPersonDataEvent.ChangeAddressOfBirthEvent(it.take(300)))
                    },
                    label = {
                        Text(text = "Место рождения")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.series,
                    onValueChange = {
                        if (it.isNumeric()) viewModel.onEvent(
                            FillPersonDataEvent.ChangeSeriesEvent(
                                it.take(
                                    4
                                )
                            )
                        )
                    },
                    label = {
                        Text(text = "Серия паспорта")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.code,
                    onValueChange = {
                        if (it.isNumeric()) viewModel.onEvent(
                            FillPersonDataEvent.ChangeCodeEvent(
                                it.take(
                                    6
                                )
                            )
                        )
                    },
                    label = {
                        Text(text = "Номер паспорта")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
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
                            onClick = { viewModel.onEvent(FillPersonDataEvent.ChangeSexEvent(true)) },
                            shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = if (state.sex) Primary else Color.Transparent,
                                contentColor = if (state.sex) Color.White else Color.Unspecified
                            ),
                            border = BorderStroke(width = 1.dp, color = Primary)
                        ) {
                            Text(text = "Муж")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedButton(
                            onClick = { viewModel.onEvent(FillPersonDataEvent.ChangeSexEvent(false)) },
                            shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = if (!state.sex) Primary else Color.Transparent,
                                contentColor = if (!state.sex) Color.White else Color.Unspecified
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
                    interactionSource = issueDateInteractionSource,
                    value = if (state.issueDate.selectedDateMillis != null) SimpleDateFormat(
                        "dd.MM.yyyy"
                    ).format(
                        Date(state.issueDate.selectedDateMillis!!)
                    )
                    else "не выбрана",
                    onValueChange = {},
                    label = {
                        Text(text = "Дата выдачи паспорта")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.issueOrganization,
                    onValueChange = {
                        viewModel.onEvent(
                            FillPersonDataEvent.ChangeIssueOrganizationEvent(
                                it.take(
                                    100
                                )
                            )
                        )
                    },
                    label = {
                        Text(text = "Паспорт выдан")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.departmentCode,
                    onValueChange = {
                        if (it.isNumeric() || it.contains("-")) if (it.isDepartmentCode()) viewModel.onEvent(
                            FillPersonDataEvent.ChangeDepartmentCodeEvent(
                                it.take(
                                    7
                                )
                            )
                        )
                    },
                    label = {
                        Text(text = "Код подразделения")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Дополнительные данные",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.registrationAddress,
                    onValueChange = {
                        viewModel.onEvent(
                            FillPersonDataEvent.ChangeRegistrationAddressEvent(
                                it.take(
                                    300
                                )
                            )
                        )
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
                    value = state.liveAddress,
                    onValueChange = {
                        viewModel.onEvent(FillPersonDataEvent.ChangeLiveAddressEvent(it.take(300)))
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
                    value = state.policy,
                    onValueChange = {
                        if (it.isNumeric()) viewModel.onEvent(
                            FillPersonDataEvent.ChangePolicyEvent(
                                it.take(
                                    16
                                )
                            )
                        )
                    },
                    label = {
                        Text(text = "Полис")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.insuranceCompany,
                    onValueChange = {
                        viewModel.onEvent(
                            FillPersonDataEvent.ChangeInsuranceCompanyEvent(
                                it.take(
                                    300
                                )
                            )
                        )
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
                    value = state.height.toString(),
                    onValueChange = {
                        if (it.isNumeric()) viewModel.onEvent(
                            FillPersonDataEvent.ChangeHeightEvent(
                                it.toInt()
                            )
                        )
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
                    value = state.weight.toString(),
                    onValueChange = {
                        if (it.isNumeric()) viewModel.onEvent(
                            FillPersonDataEvent.ChangeWeightEvent(
                                it.toFloat()
                            )
                        )
                    },
                    label = {
                        Text(text = "Вес")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.onEvent(FillPersonDataEvent.OnClickButtonEvent)
                    },
                    shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
                    colors = ButtonDefaults.buttonColors(contentColor = Color.White),
                    enabled = state.valid
                ) {
                    Text(text = "Зарегистрироваться")
                }
                Spacer(modifier = Modifier.height(24.dp))

                if (dateOfBirthInteractionSource.collectIsPressedAsState().value) viewModel.onEvent(
                    FillPersonDataEvent.ShowDateOfBirthPickerDialogEvent
                )
                if (issueDateInteractionSource.collectIsPressedAsState().value) viewModel.onEvent(
                    FillPersonDataEvent.ShowIssueDatePickerDialogEvent
                )

                if (state.nowSelectableIssueDate || state.nowSelectableDateOfBirth) {
                    DatePickerDialog(onDismissRequest = { }, confirmButton = {
                        Button(onClick = { viewModel.onEvent(FillPersonDataEvent.HideDatePickerDialogEvent) }) {
                            Text(text = "Подтвердить", color = Color.White)
                        }
                    }) {
                        DatePicker(state = if (state.nowSelectableDateOfBirth) state.dateOfBirth else state.issueDate)
                    }
                }

                if (state.contentState.isLoading.value) LoadingDialog(
                    onDismissRequest = {},
                    isDarkTheme = isDarkTheme
                )

                if (state.showDialog) ContentDialog(text = if (state.contentState.exception.value != null) state.contentState.exception.value?.message.toString() else state.contentState.data.value.toString(),
                    onDismissRequest = {
                        viewModel.onEvent(FillPersonDataEvent.HideDialogEvent)
                    }, isDarkTheme = isDarkTheme
                )

                if (state.success) {
                    navController.navigate(Route.HomeScreen.route)
                }
            }
        }
    }
}