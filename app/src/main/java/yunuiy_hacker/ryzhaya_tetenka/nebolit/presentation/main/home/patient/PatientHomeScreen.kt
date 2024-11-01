package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.home.patient

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.timeToString
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toPassportDate
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.common.model.TimeOfDay
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.ContentDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.LoadingDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph.BottomNavigation
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.BUTTON_CORNER_RADIUS

@Composable
fun PatientHomeScreen(
    navController: NavHostController, viewModel: PatientHomeViewModel = hiltViewModel()
) {
    val isDarkTheme = viewModel.dataStoreHelper.getTheme().collectAsState(initial = false).value

    val timeOfDay: TimeOfDay = viewModel.defineTimeOfDayUseCase.execute()

    LaunchedEffect(Unit) {
        viewModel.onEvent(PatientHomeEvent.GetAppointmentsEvent)
    }

    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) },
        containerColor = MaterialTheme.colorScheme.onBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = buildAnnotatedString {
                append(
                    when (timeOfDay) {
                        TimeOfDay.MORNING -> "Доброе утро"
                        TimeOfDay.AFTERNOON -> "Добрый день"
                        TimeOfDay.EVENING -> "Добрый вечер"
                        TimeOfDay.NIGHT -> "Доброй ночи"
                    }
                )
                append(", ")
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    viewModel.state.patient.user?.passport.let { passport ->
                        append(passport?.name)
                        append(" ")
                        append(passport?.lastname)
                    }
                }
            }, color = MaterialTheme.colorScheme.onSurface, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Ваши записи", color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(12.dp))
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = { },
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 16.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                shape = RoundedCornerShape(
                    BUTTON_CORNER_RADIUS
                )
            ) {
                Column {
                    if (viewModel.state.appointments.size > 0) {
                        LazyColumn(
                            modifier = Modifier.padding(12.dp)
                        ) {
                            items(viewModel.state.appointments) { item ->
                                Column {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(text = buildAnnotatedString {
                                        append(item.doctorSchedule?.doctor?.let { doctor ->
                                            doctor.user?.passport?.let { passport ->
                                                doctor.specialization?.title + " " + passport.name + " " + passport.lastname + " " + item.doctorSchedule.date.toPassportDate() + " в " + item.doctorSchedule.time.timeToString()
                                            }
                                        })
                                    }, color = MaterialTheme.colorScheme.onSurface)
                                    if (item != viewModel.state.appointments.last()) {
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Divider()
                                        Spacer(modifier = Modifier.height(8.dp))
                                    }
                                }
                            }
                        }
                    } else {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "У вас еще нет записей",
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(), onClick = {
                    navController.navigate(Route.SelectSpecializationScreen.route)
                }, shape = RoundedCornerShape(
                    BUTTON_CORNER_RADIUS
                ), colors = ButtonDefaults.buttonColors(contentColor = Color.White)
            ) {
                Text(text = "Записаться на прием")
            }
        }
    }

    if (viewModel.state.contentState.isLoading.value) {
        LoadingDialog(onDismissRequest = {}, isDarkTheme = isDarkTheme)
    }

    if (viewModel.state.showDialog) ContentDialog(
        text = if (viewModel.state.contentState.data.value == null) viewModel.state.contentState.exception.value?.message.toString() else viewModel.state.contentState.data.value.toString(),
        onDismissRequest = { viewModel.onEvent(PatientHomeEvent.HideDialogEvent) },
        isDarkTheme = isDarkTheme
    )
}