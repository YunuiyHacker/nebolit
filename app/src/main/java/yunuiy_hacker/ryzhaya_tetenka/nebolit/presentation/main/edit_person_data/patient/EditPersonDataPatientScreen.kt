package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.edit_person_data.patient

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.JoinInner
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.material.icons.filled.Terminal
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
fun EditPersonDataPatientScreen(
    navController: NavHostController,
    viewModel: EditPersonDataPatientViewModel = hiltViewModel()
) {
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
                        text = "Редактирование данных пациента",
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
                containerColor = if (viewModel.dataStoreHelper.getTheme()
                        .collectAsState(initial = false).value
                ) Color(
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = state.surname,
                        onValueChange = {
                            viewModel.onEvent(
                                EditPersonDataPatientEvent.ChangeSurnameEvent(
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
                                EditPersonDataPatientEvent.ChangeNameEvent(
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
                                EditPersonDataPatientEvent.ChangeLastnameEvent(
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
                        value = state.registrationAddress,
                        onValueChange = {
                            viewModel.onEvent(
                                EditPersonDataPatientEvent.ChangeRegistrationAddressEvent(
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
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.registrationAddressValid) Icons.Default.Done else Icons.Default.Error,
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
                        value = state.liveAddress,
                        onValueChange = {
                            viewModel.onEvent(
                                EditPersonDataPatientEvent.ChangeLiveAddressEvent(
                                    it.take(300)
                                )
                            )
                        },
                        label = {
                            Text(text = "Фактический адрес")
                        },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.liveAddressValid) Icons.Default.Done else Icons.Default.Error,
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
                        value = state.policy,
                        onValueChange = {
                            if (it.isNumeric()) viewModel.onEvent(
                                EditPersonDataPatientEvent.ChangePolicyEvent(
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
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.policyValid) Icons.Default.Done else Icons.Default.Error,
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
                        value = state.insuranceCompany,
                        onValueChange = {
                            viewModel.onEvent(
                                EditPersonDataPatientEvent.ChangeInsuranceCompanyEvent(
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
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.insuranceCompanyValid) Icons.Default.Done else Icons.Default.Error,
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
                        value = state.height.toString(),
                        onValueChange = {
                            if (it.isNumeric()) viewModel.onEvent(
                                EditPersonDataPatientEvent.ChangeHeightEvent(
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
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.heightValid) Icons.Default.Done else Icons.Default.Error,
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
                        value = state.weight.toString(),
                        onValueChange = {
                            if (it.isNumeric()) viewModel.onEvent(
                                EditPersonDataPatientEvent.ChangeWeightEvent(
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
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (state.weightValid) Icons.Default.Done else Icons.Default.Error,
                        contentDescription = "",
                        tint = Primary
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.onEvent(EditPersonDataPatientEvent.OnClickButtonEvent)
                    },
                    shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
                    colors = ButtonDefaults.buttonColors(contentColor = Color.White),
                    enabled = state.valid
                ) {
                    Text(text = "Подтвердить")
                }
                Spacer(modifier = Modifier.height(24.dp))

                if (dateOfBirthInteractionSource.collectIsPressedAsState().value) viewModel.onEvent(
                    EditPersonDataPatientEvent.ShowDateOfBirthPickerDialogEvent
                )
                if (issueDateInteractionSource.collectIsPressedAsState().value) viewModel.onEvent(
                    EditPersonDataPatientEvent.ShowIssueDatePickerDialogEvent
                )

                if (state.nowSelectableIssueDate || state.nowSelectableDateOfBirth) {
                    DatePickerDialog(onDismissRequest = { }, confirmButton = {
                        Button(onClick = { viewModel.onEvent(EditPersonDataPatientEvent.HideDatePickerDialogEvent) }) {
                            Text(text = "Подтвердить", color = Color.White)
                        }
                    }) {
                        DatePicker(state = state.dateOfBirth)
                    }
                }

                if (state.contentState.isLoading.value) LoadingDialog(onDismissRequest = {})

                if (state.showDialog) ContentDialog(text = if (state.contentState.exception.value != null) state.contentState.exception.value?.message.toString() else state.contentState.data.value.toString(),
                    onDismissRequest = {
                        viewModel.onEvent(EditPersonDataPatientEvent.HideDialogEvent)
                    })

                if (state.success) {
                    navController.navigate(Route.ProfileScreen.route)
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    EditPersonDataPatientScreen(navController = rememberNavController())
}