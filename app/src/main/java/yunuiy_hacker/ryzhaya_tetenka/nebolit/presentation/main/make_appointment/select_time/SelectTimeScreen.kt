package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.make_appointment.select_time

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.timeToString
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toPassportDate
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.PatientScheduleTable
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.ContentDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.LoadingDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.BUTTON_CORNER_RADIUS
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.DIALOG_CORNER_RADIUS

@Composable
fun SelectTimeScreen(
    navController: NavHostController, viewModel: SelectTimeViewModel = hiltViewModel()
) {
    val isDarkTheme = viewModel.dataStoreHelper.getTheme().collectAsState(initial = false).value

    LaunchedEffect(Unit) {
        viewModel.onEvent(SelectTimeEvent.GetDoctorSchedules)
    }

    viewModel.state.let { state ->
        Scaffold(containerColor = MaterialTheme.colorScheme.onBackground, topBar = {
            TopAppBar(title = {
                Column {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(x = -24.dp),
                        text = "Запись на прием",
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
        }, bottomBar = {
            if (state.doctorSchedules.isEmpty()) Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp), onClick = {
                    navController.popBackStack()
                }, shape = RoundedCornerShape(
                    BUTTON_CORNER_RADIUS
                ), colors = ButtonDefaults.buttonColors(contentColor = Color.White)
            ) {
                Text(text = "Вернуться обратно")
            }
        }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = "Расписание врача",
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(24.dp))
                PatientScheduleTable(modifier = Modifier.padding(PaddingValues(horizontal = 24.dp)),
                    doctorSchedules = state.doctorSchedules,
                    onChangeSelected = {
                        viewModel.onEvent(SelectTimeEvent.SelectDoctorSchedule(it))
                        viewModel.onEvent(SelectTimeEvent.ShowMakeAppointmentEvent)
                    })
                Spacer(modifier = Modifier.height(24.dp))
                Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                    Row {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.77f),
                                    shape = RoundedCornerShape(6.dp)
                                )
                        )
                        Spacer(modifier = Modifier.width(24.dp))
                        Text(text = "Свободная ячейка", color = MaterialTheme.colorScheme.onSurface)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.surface,
                                    shape = RoundedCornerShape(6.dp)
                                )
                        )
                        Spacer(modifier = Modifier.width(24.dp))
                        Text(text = "Занятая ячейки", color = MaterialTheme.colorScheme.onSurface)
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
        onDismissRequest = { viewModel.onEvent(SelectTimeEvent.HideDialogEvent) },
        isDarkTheme = isDarkTheme
    )

    if (viewModel.state.showMakeAppointment) Dialog(onDismissRequest = {
        viewModel.onEvent(
            SelectTimeEvent.HideMakeAppointmentEvent
        )
    }) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(DIALOG_CORNER_RADIUS))
                .background(
                    color = if (isDarkTheme) Color(0xFF131313) else Color(
                        0xFFFFFFFF
                    )
                )
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = buildAnnotatedString {
                        append("Запись на прием к ")
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            append(viewModel.state.doctorSchedule.doctor?.user?.passport?.let { passport ->
                                passport.surname + " " + passport.name + " " + passport.lastname
                            })
                        }
                        append(" по специальности ")
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            append(
                                viewModel.state.doctorSchedule.doctor?.specialization?.title?.toLowerCase()
                                    ?: "не указано"
                            )
                        }
                        append(" на дату ")
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            append(viewModel.state.doctorSchedule.date.toPassportDate())
                        }
                        append(" и время ")
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            append(viewModel.state.doctorSchedule.time.timeToString())
                        }
                        append(" в ")
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            append(viewModel.state.doctorSchedule.cabinet?.title)
                        }
                        append(" кабинет")
                    }, color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row {
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 12.dp),
                        onClick = { viewModel.onEvent(SelectTimeEvent.HideMakeAppointmentEvent) },
                        shape = RoundedCornerShape(
                            BUTTON_CORNER_RADIUS
                        ),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 24.dp)
                    ) {
                        Text(
                            text = "Отмена",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 15.sp
                        )
                    }
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 12.dp), onClick = {
                            viewModel.onEvent(SelectTimeEvent.MakeAppointmentEvent)
                        }, shape = RoundedCornerShape(
                            BUTTON_CORNER_RADIUS
                        ), elevation = ButtonDefaults.buttonElevation(defaultElevation = 24.dp)
                    ) {
                        Text(
                            text = "Записаться", color = Color.White, fontSize = 15.sp
                        )
                    }

                }
            }
        }
    }
}