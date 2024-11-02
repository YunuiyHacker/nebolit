package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.home.doctor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.sexToString
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toPassportDate
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.DoctorScheduleTable
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.ContentDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.LoadingDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph.BottomNavigation
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.BUTTON_CORNER_RADIUS

@Composable
fun DoctorHomeScreen(
    navController: NavHostController, viewModel: DoctorHomeViewModel = hiltViewModel()
) {
    val isDarkTheme = viewModel.dataStoreHelper.getTheme().collectAsState(initial = false).value

    LaunchedEffect(Unit) {
        viewModel.onEvent(DoctorHomeEvent.GetDoctorSchedules)
    }

    viewModel.state.let { state ->
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { BottomNavigation(navController = navController) },
            containerColor = MaterialTheme.colorScheme.onBackground
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = "Мое расписание",
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(24.dp))
                DoctorScheduleTable(modifier = Modifier.padding(PaddingValues(horizontal = 24.dp)),
                    doctorSchedules = state.doctorSchedules,
                    onChangeSelected = {
                        viewModel.onEvent(DoctorHomeEvent.SelectDoctorSchedule(it))
                        viewModel.onEvent(DoctorHomeEvent.ShowPatientInfoEvent)
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
                        Text(text = "Есть запись", color = MaterialTheme.colorScheme.onSurface)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.17f),
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
                        Text(text = "Нет записи", color = MaterialTheme.colorScheme.onSurface)
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
        onDismissRequest = { viewModel.onEvent(DoctorHomeEvent.HideDialogEvent) },
        isDarkTheme = isDarkTheme
    )

    if (viewModel.state.showPatientInfo) {
        ModalBottomSheet(containerColor = MaterialTheme.colorScheme.secondaryContainer,
            onDismissRequest = { viewModel.onEvent(DoctorHomeEvent.HidePatientInfoEvent) }) {
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Информация о пациенте",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = buildAnnotatedString {
                    append("ФИО: ")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        viewModel.state.patient.user?.passport?.let { passport ->
                            append("${passport.surname} ${passport.name} ${passport.lastname}")
                        }
                    }
                }, color = MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = buildAnnotatedString {
                    append("Дата рождения: ")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        viewModel.state.patient.user?.passport?.let { passport ->
                            append(passport.dateOfBirth.toPassportDate())
                        }
                    }
                }, color = MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = buildAnnotatedString {
                    append("Пол: ")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        viewModel.state.patient.user?.passport?.let { passport ->
                            append(passport.sex?.sexToString())
                        }
                    }
                }, color = MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(), onClick = {
                        navController.navigate(
                            Route.ReceivePatientScreen.withDoctorScheduleArgument(
                                viewModel.state.doctorSchedule
                            )
                        )
                    }, shape = RoundedCornerShape(
                        BUTTON_CORNER_RADIUS
                    ), colors = ButtonDefaults.buttonColors(contentColor = Color.White)
                ) {
                    Text(text = "Принять")
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}