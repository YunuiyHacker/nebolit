package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.profile.doctor

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material.icons.filled.Person4
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.PassportComposable
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.SettingsComposable
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.profile.patient.PatientProfileEvent
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph.BottomNavigation
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorProfileScreen(
    navController: NavHostController, viewModel: DoctorProfileViewModel = hiltViewModel()
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    viewModel.state.let { state ->
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
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.background(
                                if (viewModel.dataStoreHelper.getTheme()
                                        .collectAsState(initial = false).value
                                ) Primary else Color.LightGray,
                                shape = CircleShape
                            ),
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(60.dp)
                                    .padding(8.dp),
                                imageVector = Icons.Filled.Person,
                                contentDescription = "",
                                tint = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "${viewModel.state.doctor.user?.passport?.surname} ${viewModel.state.doctor.user?.passport?.name} ${viewModel.state.doctor.user?.passport?.lastname}",
                                fontSize = 18.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = viewModel.state.doctor.user?.email!!,
                                fontSize = 14.sp,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                    IconButton(onClick = { viewModel.onEvent(DoctorProfileEvent.ShowSettingsEvent) }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.onEvent(DoctorProfileEvent.ShowPassportEvent)
                    },
                    colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 16.dp)
                ) {
                    Column(modifier = Modifier.padding(paddingValues = PaddingValues(16.dp))) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.DocumentScanner,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Паспорт")
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Специальность",
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(text = viewModel.state.doctor.specialization?.title!!, color = Primary)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Номер лицензии",
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(text = viewModel.state.doctor.licenzeNumber!!, color = Primary)
                }
            }
        }

        if (state.passportVisible) {
            ModalBottomSheet(containerColor = MaterialTheme.colorScheme.secondaryContainer,
                onDismissRequest = { viewModel.onEvent(DoctorProfileEvent.HidePassportEvent) }) {
                PassportComposable(user = state.doctor?.user!!)
            }
        }

        if (state.settingsVisible) {
            ModalBottomSheet(containerColor = MaterialTheme.colorScheme.secondaryContainer,
                onDismissRequest = { viewModel.onEvent(DoctorProfileEvent.HideSettingsEvent) }) {
                SettingsComposable(darkThemeChecked = viewModel.dataStoreHelper.getTheme()
                    .collectAsState(
                        initial = false
                    ).value, onDarkThemeOnChange = {
                    viewModel.onEvent(DoctorProfileEvent.SetThemeEvent(it))
                }, onExitRowClick = {
                    viewModel.onEvent(DoctorProfileEvent.ExitEvent)
                    navController.navigate(Route.SignInScreen.route)
                })
            }
        }
    }
}