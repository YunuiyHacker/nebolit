package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.doctors.doctors_schedules

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.datetime.LocalTime
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DoctorSchedule
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.AdminDoctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.AdminScheduleTable
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.ContentDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.LoadingDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.BUTTON_CORNER_RADIUS
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun AdminDoctorsSchedulesScreen(
    navController: NavHostController,
    viewModel: AdminDoctorsSchedulesViewModel = hiltViewModel(),
    adminDoctor: AdminDoctor
) {
    val isDarkTheme = viewModel.dataStoreHelper.getTheme().collectAsState(initial = false).value

    val appointmentDateInteractionSource = remember {
        MutableInteractionSource()
    }

    val timePickerState = rememberTimePickerState(
        initialHour = if (viewModel.state.selectedDoctorSchedule.time != null) viewModel.state.selectedDoctorSchedule.time!!.hour else 0,
        initialMinute = if (viewModel.state.selectedDoctorSchedule.time != null) viewModel.state.selectedDoctorSchedule.time!!.minute else 0
    )
    var dropdownMenuExpanded by remember {
        mutableStateOf(false)
    }

    viewModel.state.let { state ->
        LaunchedEffect(Unit) {
            state.selectedDoctorSchedule = DoctorSchedule(
                id = 0, doctorId = adminDoctor.id, time = LocalTime(hour = 0, minute = 0)
            )
            viewModel.onEvent(AdminDoctorsSchedulesEvent.GetSchedulesByDoctorAndCabinetsEvent)
        }

        Scaffold(topBar = {
            TopAppBar(title = {
                Column {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(x = -24.dp),
                        text = "Расписание врача",
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
                modifier = Modifier.padding(it)
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = adminDoctor.surname + " " + adminDoctor.name + " " + adminDoctor.lastname,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(24.dp))
                AdminScheduleTable(
                    modifier = Modifier.padding(PaddingValues(horizontal = 24.dp)),
                    doctorSchedules = state.doctorSchedules,
                    selectedSchedule = state.selectedDoctorSchedule,
                    onChangeSelected = {
                        state.selectedDoctorSchedule = it
                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
                Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            viewModel.onEvent(AdminDoctorsSchedulesEvent.ShowScheduleDialogEvent)
                        },
                        shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White)
                    ) {
                        Text(text = "Добавить запись")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if (viewModel.state.selectedDoctorSchedule.id == 0) {
                                viewModel.state.contentState.data.value = "Выберите запись"
                                viewModel.state.showDialog = true
                            } else {
                                viewModel.onEvent(AdminDoctorsSchedulesEvent.EditScheduleEvent)
                            }
                        },
                        shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White)
                    ) {
                        Text(text = "Редактировать запись")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if (viewModel.state.selectedDoctorSchedule.id == 0) {
                                viewModel.state.contentState.data.value = "Выберите запись"
                                viewModel.state.showDialog = true
                            } else {
                                viewModel.onEvent(AdminDoctorsSchedulesEvent.RemoveScheduleEvent)
                            }
                        },
                        shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White)
                    ) {
                        Text(text = "Удалить запись")
                    }
                }
            }
        }
    }

    if (viewModel.state.contentState.isLoading.value) {
        LoadingDialog(onDismissRequest = {}, isDarkTheme = isDarkTheme)
    }

    if (viewModel.state.showDialog) ContentDialog(
        text = if (viewModel.state.contentState.data.value == null) viewModel.state.contentState.exception.value?.message.toString() else viewModel.state.contentState.data.value.toString(),
        onDismissRequest = { viewModel.onEvent(AdminDoctorsSchedulesEvent.HideDialogEvent) },
        isDarkTheme = isDarkTheme
    )

    if (viewModel.state.showScheduleDialog) ModalBottomSheet(containerColor = MaterialTheme.colorScheme.secondaryContainer,
        onDismissRequest = { viewModel.onEvent(AdminDoctorsSchedulesEvent.HideScheduleDialogEvent) }) {
        Text(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            text = "Расписания",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            interactionSource = appointmentDateInteractionSource,
            value = if (viewModel.state.appointmentDate.selectedDateMillis != null) SimpleDateFormat(
                "dd.MM.yyyy"
            ).format(
                Date(viewModel.state.appointmentDate.selectedDateMillis!!)
            )
            else "не выбрана",
            onValueChange = {},
            label = {
                Text(text = "Дата записи")
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        TimePicker(
            modifier = Modifier.fillMaxWidth(),
            state = timePickerState,
            layoutType = TimePickerLayoutType.Vertical
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier.padding(start = 24.dp, top = 24.dp),
            text = "Кабинет",
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .clickable {
                dropdownMenuExpanded = !dropdownMenuExpanded
            }
            .background(
                color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(12.dp)
            )) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = if (viewModel.state.selectedCabinet.id == 0) "Выберите кабинет" else viewModel.state.selectedCabinet.title.toString(),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        DropdownMenu(modifier = Modifier,
            expanded = dropdownMenuExpanded,
            onDismissRequest = { dropdownMenuExpanded = false }) {
            viewModel.state.cabinets.forEachIndexed { index, cabinet ->
                DropdownMenuItem(text = {
                    Text(
                        text = cabinet.title.toString(),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }, onClick = {
                    viewModel.state.selectedCabinet = cabinet
                    dropdownMenuExpanded = false
                })
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 24.dp, end = 12.dp, bottom = 24.dp),
                onClick = { viewModel.onEvent(AdminDoctorsSchedulesEvent.HideScheduleDialogEvent) },
                shape = RoundedCornerShape(
                    BUTTON_CORNER_RADIUS
                ),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 24.dp)
            ) {
                Text(
                    text = "Отмена", color = MaterialTheme.colorScheme.onSurface, fontSize = 15.sp
                )
            }
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp, end = 24.dp, bottom = 24.dp),
                onClick = {
                    viewModel.onEvent(
                        AdminDoctorsSchedulesEvent.SaveScheduleEvent(
                            timePickerState.hour,
                            timePickerState.minute,
                            viewModel.state.selectedCabinet.id!!
                        )
                    )
                },
                shape = RoundedCornerShape(
                    BUTTON_CORNER_RADIUS
                ),
                enabled = viewModel.state.selectedCabinet.id != 0,
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 24.dp)
            ) {
                Text(
                    text = "Сохранить", color = Color.White, fontSize = 15.sp
                )
            }
        }
    }

    if (appointmentDateInteractionSource.collectIsPressedAsState().value) viewModel.onEvent(
        AdminDoctorsSchedulesEvent.ShowAppointmentDateDialogEvent
    )

    if (viewModel.state.showAppointmentDataDialog) {
        DatePickerDialog(onDismissRequest = { }, confirmButton = {
            Button(onClick = { viewModel.onEvent(AdminDoctorsSchedulesEvent.HideAppointmentDateDialogEvent) }) {
                Text(text = "Подтвердить", color = Color.White)
            }
        }) {
            DatePicker(state = viewModel.state.appointmentDate)
        }
    }
}