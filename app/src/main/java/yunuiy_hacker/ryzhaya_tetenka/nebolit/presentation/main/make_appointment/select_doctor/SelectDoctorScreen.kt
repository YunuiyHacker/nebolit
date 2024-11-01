package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.make_appointment.select_doctor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.ContentDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.LoadingDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.BUTTON_CORNER_RADIUS

@Composable
fun SelectDoctorScreen(
    navController: NavHostController, viewModel: SelectDoctorViewModel = hiltViewModel()
) {
    val isDarkTheme = viewModel.dataStoreHelper.getTheme().collectAsState(initial = false).value

    LaunchedEffect(Unit) {
        viewModel.onEvent(SelectDoctorEvent.GetDoctorsBySpecializationEvent)
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
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
                onClick = {
                    if (state.doctors.size > 0) navController.navigate(
                        Route.SelectTimeScreen.withIntArguments(
                            state.doctorId
                        )
                    ) else
                        navController.popBackStack()
                },
                shape = RoundedCornerShape(
                    BUTTON_CORNER_RADIUS
                ),
                enabled = if (state.doctors.size > 0) viewModel.state.doctorId != 0 else true,
                colors = ButtonDefaults.buttonColors(contentColor = Color.White)
            ) {
                Text(text = if (state.doctors.size > 0) "Продолжить" else "Вернуться обратно")
            }
        }) {
            Column(
                modifier = if (state.doctors.size > 0) Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()) else Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Выберите врача", color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(12.dp))
                if (state.doctors.size > 0) {
                    state.doctors.forEach { doctor ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp)
                                .clickable {
                                    viewModel.onEvent(
                                        SelectDoctorEvent.SelectCurrentDoctorEvent(
                                            doctor
                                        )
                                    )
                                }
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = doctor.user?.passport.let { passport -> passport?.surname + " " + passport?.name + " " + passport?.lastname },
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            if (state.doctorId == doctor.id!!)
                                Row(
                                    modifier = Modifier
                                        .width(20.dp)
                                        .height(2.dp)
                                        .background(
                                            color = MaterialTheme.colorScheme.primary,
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                ) {}
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Не нашли подходящих врачей",
                            color = MaterialTheme.colorScheme.onSurface
                        )
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
        onDismissRequest = { viewModel.onEvent(SelectDoctorEvent.HideDialogEvent) },
        isDarkTheme = isDarkTheme
    )
}