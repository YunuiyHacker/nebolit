package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.patients.patients_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.sexToString
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.AdminPatient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.PersonDataTable
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.ContentDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog.LoadingDialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.BUTTON_CORNER_RADIUS

@Composable
fun AdminPatientsListScreen(
    navController: NavHostController, viewModel: AdminPatientsListViewModel = hiltViewModel()
) {
    val isDarkTheme = viewModel.dataStoreHelper.getTheme().collectAsState(initial = false).value

    Scaffold(topBar = {
        TopAppBar(title = {
            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = -24.dp),
                    text = "Список пациентов",
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
                .padding(horizontal = 24.dp)
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            val cellWidth: (Int) -> Dp = { index ->
                when (index) {
                    3 -> 400.dp
                    5 -> 450.dp
                    6 -> 100.dp
                    7 -> 100.dp
                    8 -> 100.dp
                    10 -> 450.dp
                    11 -> 150.dp
                    12 -> 450.dp
                    13 -> 450.dp
                    16 -> 100.dp
                    17 -> 100.dp
                    else -> 250.dp
                }
            }
            val headerCellTitle: @Composable (Int) -> Unit = { index ->
                val value = when (index) {
                    0 -> "Фамилия"
                    1 -> "Имя"
                    2 -> "Отчество"
                    3 -> "Почта"
                    4 -> "Дата рождения"
                    5 -> "Место рождения"
                    6 -> "Серия"
                    7 -> "Номер"
                    8 -> "Пол"
                    9 -> "Дата выдачи"
                    10 -> "Паспорт выдан"
                    11 -> "Код подразделения"
                    12 -> "Адрес регистрации"
                    13 -> "Фактический адрес"
                    14 -> "Страховая компания"
                    15 -> "Полис"
                    16 -> "Рост"
                    17 -> "Вес"
                    else -> ""
                }

                Text(
                    text = value,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            val cellText: @Composable (Int, AdminPatient) -> Unit = { index, item ->
                val value = when (index) {
                    0 -> item.surname
                    1 -> item.name
                    2 -> item.lastname
                    3 -> item.email
                    4 -> item.dateOfBirth
                    5 -> item.addressOfBirth
                    6 -> item.series.toString()
                    7 -> item.code.toString()
                    8 -> item.sex.sexToString()
                    9 -> item.issueDate
                    10 -> item.issueOrganization
                    11 -> item.departmentCode
                    12 -> item.registrationAddress
                    13 -> item.liveAddress
                    14 -> item.insuranceCompany
                    15 -> item.policy
                    16 -> item.height.toString()
                    17 -> item.weight.toString()
                    else -> ""
                }

                Text(
                    text = value,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            PersonDataTable(modifier = Modifier.clip(RoundedCornerShape(20.dp)),
                columnCount = 17,
                cellWidth = cellWidth,
                data = viewModel.state.patients,
                headerCellContent = headerCellTitle,
                cellContent = cellText,
                color = MaterialTheme.colorScheme.primary,
                selectedIndex = viewModel.state.tableSelectedPatientIndex,
                onChangeSelectedIndex = {
                    viewModel.onEvent(AdminPatientsListEvent.ChangeSelectedPatientIndexEvent(it))
                })
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (viewModel.state.tableSelectedPatientIndex == -2) {
                        viewModel.state.contentState.data.value = "Выберите пациента"
                        viewModel.state.showDialog = true
                    } else {
                        navController.navigate(
                            Route.AdminPatientsEditScreen.withAdminPatientArgument(
                                viewModel.state.patients[viewModel.state.tableSelectedPatientIndex]
                            )
                        )
                    }
                },
                shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
                colors = ButtonDefaults.buttonColors(contentColor = Color.White)
            ) {
                Text(text = "Редактировать")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.onEvent(AdminPatientsListEvent.DeletePatientEvent)
                },
                shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
                colors = ButtonDefaults.buttonColors(contentColor = Color.White)
            ) {
                Text(text = "Удалить")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate(Route.AdminPatientsAddScreen.route)
                },
                shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
                colors = ButtonDefaults.buttonColors(contentColor = Color.White)
            ) {
                Text(text = "Добавить нового пациента")
            }
        }
    }

    if (viewModel.state.contentState.isLoading.value) {
        LoadingDialog(onDismissRequest = {}, isDarkTheme = isDarkTheme)
    }

    if (viewModel.state.showDialog) ContentDialog(text = if (viewModel.state.contentState.data == null) viewModel.state.contentState.exception.value.toString() else viewModel.state.contentState.data.value.toString(),
        onDismissRequest = { viewModel.onEvent(AdminPatientsListEvent.HideDialogEvent) },
        isDarkTheme = isDarkTheme
    )

}