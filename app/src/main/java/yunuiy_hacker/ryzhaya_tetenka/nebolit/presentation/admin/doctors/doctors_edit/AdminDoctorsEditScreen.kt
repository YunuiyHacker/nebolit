package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.doctors.doctors_edit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.isDepartmentCode
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.isNumeric
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toDateOfMobileFormat
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.AdminDoctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.ContentDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.LoadingDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.BUTTON_CORNER_RADIUS
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.Primary
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun AdminDoctorsEditScreen(
    navController: NavHostController,
    viewModel: AdminDoctorsEditViewModel = hiltViewModel(),
    adminDoctor: AdminDoctor
) {
    val isDarkTheme = viewModel.dataStoreHelper.getTheme().collectAsState(initial = false).value

    val dateOfBirthInteractionSource = remember {
        MutableInteractionSource()
    }
    val issueDateInteractionSource = remember {
        MutableInteractionSource()
    }
    var dropdownMenuExpanded by remember {
        mutableStateOf(false)
    }

    viewModel.state.let { state ->
        LaunchedEffect(Unit) {
            viewModel.onEvent(AdminDoctorsEditEvent.GetAllSpecializations)

            state.email = adminDoctor.email
            state.password = adminDoctor.password
            state.surname = adminDoctor.surname
            state.name = adminDoctor.name
            state.lastname = adminDoctor.lastname
            state.dateOfBirth.selectedDateMillis =
                adminDoctor.dateOfBirth.toDateOfMobileFormat().time
            state.addressOfBirth = adminDoctor.addressOfBirth
            state.series = adminDoctor.series.toString()
            state.code = adminDoctor.code.toString()
            state.issueDate.selectedDateMillis =
                adminDoctor.issueDate.toDateOfMobileFormat().time
            state.issueOrganization = adminDoctor.issueOrganization
            state.departmentCode = adminDoctor.departmentCode
            state.licenzeNumber = adminDoctor.licenzeNumber
        }

        Scaffold(topBar = {
            TopAppBar(title = {
                Column {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(x = -24.dp),
                        text = "Редактирование врача",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }, navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = if (isDarkTheme) Color(
                    0xFF131313
                ) else Color(0xFFF9F9F9)
            )
            )
        }, bottomBar = {}, containerColor = MaterialTheme.colorScheme.onBackground) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = state.email,
                        onValueChange = {
                            viewModel.onEvent(
                                AdminDoctorsEditEvent.ChangeEmailEvent(
                                    it.take(
                                        100
                                    )
                                )
                            )
                        },
                        label = {
                            Text(text = "E-mail")
                        },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.emailValid) Icons.Default.Done else Icons.Default.Error,
                        contentDescription = "",
                        tint = Primary
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = state.password,
                        onValueChange = {
                            viewModel.onEvent(
                                AdminDoctorsEditEvent.ChangePasswordEvent(
                                    it.take(
                                        100
                                    )
                                )
                            )
                        },
                        label = {
                            Text(text = "Пароль")
                        },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.passwordValid) Icons.Default.Done else Icons.Default.Error,
                        contentDescription = "",
                        tint = Primary
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = state.surname,
                        onValueChange = {
                            viewModel.onEvent(
                                AdminDoctorsEditEvent.ChangeSurnameEvent(
                                    it.take(
                                        100
                                    )
                                )
                            )
                        },
                        label = {
                            Text(text = "Фамилия")
                        },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.surnameValid) Icons.Default.Done else Icons.Default.Error,
                        contentDescription = "",
                        tint = Primary
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = state.name,
                        onValueChange = {
                            viewModel.onEvent(
                                AdminDoctorsEditEvent.ChangeNameEvent(
                                    it.take(
                                        100
                                    )
                                )
                            )
                        },
                        label = {
                            Text(text = "Имя")
                        },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.nameValid) Icons.Default.Done else Icons.Default.Error,
                        contentDescription = "",
                        tint = Primary
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = state.lastname,
                        onValueChange = {
                            viewModel.onEvent(
                                AdminDoctorsEditEvent.ChangeLastnameEvent(
                                    it.take(
                                        100
                                    )
                                )
                            )
                        },
                        label = {
                            Text(text = "Отчество")
                        },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.lastnameValid) Icons.Default.Done else Icons.Default.Error,
                        contentDescription = "",
                        tint = Primary
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        interactionSource = dateOfBirthInteractionSource,
                        value = if (state.dateOfBirth.selectedDateMillis != null) SimpleDateFormat(
                            "dd.MM.yyyy"
                        ).format(
                            Date(state.dateOfBirth.selectedDateMillis!! + (24 * 60 * 60 * 1000))
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
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.dateOfBirthValid) Icons.Default.Done else Icons.Default.Error,
                        contentDescription = "",
                        tint = Primary
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = state.addressOfBirth,
                        onValueChange = {
                            viewModel.onEvent(
                                AdminDoctorsEditEvent.ChangeAddressOfBirthEvent(
                                    it.take(
                                        300
                                    )
                                )
                            )
                        },
                        label = {
                            Text(text = "Место рождения")
                        },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.addressOfBirthValid) Icons.Default.Done else Icons.Default.Error,
                        contentDescription = "",
                        tint = Primary
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = state.series,
                        onValueChange = {
                            if (it.isNumeric()) viewModel.onEvent(
                                AdminDoctorsEditEvent.ChangeSeriesEvent(
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
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.seriesValid) Icons.Default.Done else Icons.Default.Error,
                        contentDescription = "",
                        tint = Primary
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = state.code,
                        onValueChange = {
                            if (it.isNumeric()) viewModel.onEvent(
                                AdminDoctorsEditEvent.ChangeCodeEvent(
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
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.codeValid) Icons.Default.Done else Icons.Default.Error,
                        contentDescription = "",
                        tint = Primary
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        interactionSource = issueDateInteractionSource,
                        value = if (state.issueDate.selectedDateMillis != null) SimpleDateFormat(
                            "dd.MM.yyyy"
                        ).format(
                            Date(state.issueDate.selectedDateMillis!! + (24 * 60 * 60 * 1000))
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
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.issueDateValid) Icons.Default.Done else Icons.Default.Error,
                        contentDescription = "",
                        tint = Primary
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = state.issueOrganization,
                        onValueChange = {
                            viewModel.onEvent(
                                AdminDoctorsEditEvent.ChangeIssueOrganizationEvent(
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
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.issueOrganizationValid) Icons.Default.Done else Icons.Default.Error,
                        contentDescription = "",
                        tint = Primary
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = state.departmentCode,
                        onValueChange = {
                            if (it.isNumeric() || it.contains("-")) if (it.isDepartmentCode()) viewModel.onEvent(
                                AdminDoctorsEditEvent.ChangeDepartmentCodeEvent(
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
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.departmentCodeValid) Icons.Default.Done else Icons.Default.Error,
                        contentDescription = "",
                        tint = Primary
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Специальность", color = MaterialTheme.colorScheme.onSurface)
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(BUTTON_CORNER_RADIUS))
                                .background(if (state.specializationIndex == -2 || state.specializationIndex == -1) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.primary)
                                .clickable {
                                    dropdownMenuExpanded = !dropdownMenuExpanded
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.padding(16.dp),
                                text = if (state.specializationIndex == -2 || state.specializationIndex == -1) "Выберите специальность" else state.specializations[state.specializationIndex].title.toString(),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        DropdownMenu(
                            modifier = Modifier,
                            expanded = dropdownMenuExpanded,
                            onDismissRequest = { dropdownMenuExpanded = false }) {
                            state.specializations.forEachIndexed { index, specialization ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = state.specializations[index].title.toString(),
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    },
                                    onClick = {
                                        viewModel.onEvent(
                                            AdminDoctorsEditEvent.ChangeSpecializationIndexEvent(
                                                index
                                            )
                                        )
                                        dropdownMenuExpanded = false
                                    })
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.specializationValid) Icons.Default.Done else Icons.Default.Error,
                        contentDescription = "",
                        tint = Primary
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = state.licenzeNumber,
                        onValueChange = {
                            viewModel.onEvent(
                                AdminDoctorsEditEvent.ChangeLicenzeNumberEvent(
                                    it.take(
                                        15
                                    )
                                )
                            )
                        },
                        label = {
                            Text(text = "Номер лицензии")
                        },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.licenzeNumberValid) Icons.Default.Done else Icons.Default.Error,
                        contentDescription = "",
                        tint = Primary
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.onEvent(AdminDoctorsEditEvent.OnClickButtonEvent)
                    },
                    shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
                    colors = ButtonDefaults.buttonColors(contentColor = Color.White),
                    enabled = state.valid
                ) {
                    Text(text = "Подтвердить")
                }
                Spacer(modifier = Modifier.height(24.dp))

                if (dateOfBirthInteractionSource.collectIsPressedAsState().value) viewModel.onEvent(
                    AdminDoctorsEditEvent.ShowDateOfBirthPickerDialogEvent
                )
                if (issueDateInteractionSource.collectIsPressedAsState().value) viewModel.onEvent(
                    AdminDoctorsEditEvent.ShowIssueDatePickerDialogEvent
                )

                if (state.nowSelectableIssueDate || state.nowSelectableDateOfBirth) {
                    DatePickerDialog(onDismissRequest = { }, confirmButton = {
                        Button(onClick = { viewModel.onEvent(AdminDoctorsEditEvent.HideDatePickerDialogEvent) }) {
                            Text(text = "Подтвердить", color = Color.White)
                        }
                    }) {
                        DatePicker(state = state.dateOfBirth)
                    }
                }

                if (state.contentState.isLoading.value) LoadingDialog(
                    onDismissRequest = {},
                    isDarkTheme = isDarkTheme
                )

                if (state.showDialog) ContentDialog(
                    text = if (state.contentState.exception.value != null) state.contentState.exception.value?.message.toString() else state.contentState.data.value.toString(),
                    onDismissRequest = {
                        viewModel.onEvent(AdminDoctorsEditEvent.HideDialogEvent)
                    }, isDarkTheme = isDarkTheme
                )

                if (state.success) {
                    navController.popBackStack()
                }
            }
        }
    }
}