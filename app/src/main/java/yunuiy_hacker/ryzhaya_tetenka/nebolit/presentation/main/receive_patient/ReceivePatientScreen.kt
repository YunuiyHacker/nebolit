package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.receive_patient

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.ContentDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.LoadingDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.BUTTON_CORNER_RADIUS

@Composable
fun ReceivePatientScreen(
    navController: NavHostController, viewModel: ReceivePatientViewModel = hiltViewModel()
) {
    val isDarkTheme = viewModel.dataStoreHelper.getTheme().collectAsState(initial = false).value

    var typeOfPaymentExpanded by remember {
        mutableStateOf(false)
    }
    var treatmentResultExpanded by remember {
        mutableStateOf(false)
    }
    var visitPurposeExpanded by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(ReceivePatientEvent.GetNeededData)
    }

    viewModel.state.let { state ->
        Scaffold(containerColor = MaterialTheme.colorScheme.onBackground, topBar = {
            TopAppBar(title = {
                Column {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(x = -24.dp),
                        text = "Прием пациента",
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
        }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    modifier = Modifier, text = buildAnnotatedString {
                        append("Пациент ")
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            state.doctorSchedule.appointments[0].patient?.user?.passport?.let { passport ->
                                append("${passport.surname} ${passport.name} ${passport.lastname}")
                            }
                        }
                    }, color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(
                            Route.DoctorDiseaseHistoryScreen.withIntArguments(state.doctorSchedule.appointments[0].patient?.id!!)
                        )
                    },
                    shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
                    colors = ButtonDefaults.buttonColors(contentColor = Color.White)
                ) {
                    Text(text = "История болезней")
                }
                Spacer(modifier = Modifier.height(24.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.diagnosis,
                    onValueChange = {
                        viewModel.onEvent(
                            ReceivePatientEvent.ChangeDiagnosisEvent(
                                it.take(
                                    1000
                                )
                            )
                        )
                    },
                    label = {
                        Text(text = "Диагноз")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Тип оплаты", color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(12.dp))
                DropdownMenu(modifier = Modifier,
                    expanded = typeOfPaymentExpanded,
                    onDismissRequest = { typeOfPaymentExpanded = false }) {
                    state.typesOfPayment.forEachIndexed { index, typeOfPayment ->
                        DropdownMenuItem(text = {
                            Text(
                                text = typeOfPayment.title.toString(),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }, onClick = {
                            viewModel.onEvent(
                                ReceivePatientEvent.SelectTypeOfPayment(
                                    typeOfPayment
                                )
                            )
                            typeOfPaymentExpanded = false
                        })
                    }
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(
                                BUTTON_CORNER_RADIUS
                            )
                        )
                        .clickable { typeOfPaymentExpanded = true }) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = state.typeOfPayment.title ?: "",
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Прием", color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(
                                BUTTON_CORNER_RADIUS
                            )
                        )
                        .clickable { visitPurposeExpanded = true }) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = state.visitPurpose.title ?: "",
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                DropdownMenu(modifier = Modifier,
                    expanded = visitPurposeExpanded,
                    onDismissRequest = { visitPurposeExpanded = false }) {
                    state.visitPurposes.forEachIndexed { index, visitPurpose ->
                        DropdownMenuItem(text = {
                            Text(
                                text = visitPurpose.title.toString(),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }, onClick = {
                            viewModel.onEvent(
                                ReceivePatientEvent.SelectVisitPurpose(
                                    visitPurpose
                                )
                            )
                            visitPurposeExpanded = false
                        })
                    }
                }
                if (state.visitPurpose.id == 2) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = "Результат лечения", color = MaterialTheme.colorScheme.primary)
                    DropdownMenu(modifier = Modifier,
                        expanded = treatmentResultExpanded,
                        onDismissRequest = { treatmentResultExpanded = false }) {
                        state.treatmentResults.forEachIndexed { index, treatmentResult ->
                            DropdownMenuItem(text = {
                                Text(
                                    text = treatmentResult.title.toString(),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }, onClick = {
                                viewModel.onEvent(
                                    ReceivePatientEvent.SelectTreatmentResult(
                                        treatmentResult
                                    )
                                )
                                treatmentResultExpanded = false
                            })
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(
                                    BUTTON_CORNER_RADIUS
                                )
                            )
                            .clickable { treatmentResultExpanded = true }) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = state.treatmentResult.title ?: "",
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.onEvent(ReceivePatientEvent.SaveDiseaseHistoryEvent)
                    },
                    enabled = state.valid,
                    shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
                    colors = ButtonDefaults.buttonColors(contentColor = Color.White)
                ) {
                    Text(text = "Закончить прием")
                }
            }
        }
    }

    if (viewModel.state.contentState.isLoading.value) {
        LoadingDialog(onDismissRequest = {}, isDarkTheme = isDarkTheme)
    }

    if (viewModel.state.showDialog) ContentDialog(
        text = if (viewModel.state.contentState.data.value == null) viewModel.state.contentState.exception.value?.message.toString() else viewModel.state.contentState.data.value.toString(),
        onDismissRequest = { viewModel.onEvent(ReceivePatientEvent.HideDialogEvent) },
        isDarkTheme = isDarkTheme
    )

    if (viewModel.state.succes) {
        navController.popBackStack()
    }
}