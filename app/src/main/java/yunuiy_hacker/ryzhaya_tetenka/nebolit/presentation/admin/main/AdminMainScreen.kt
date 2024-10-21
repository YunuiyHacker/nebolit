package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.SettingsComposable
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.BUTTON_CORNER_RADIUS

@Composable
fun AdminMainScreen(
    navController: NavHostController,
    viewModel: AdminMainViewModel = hiltViewModel()
) {
    viewModel.state.let { state ->

        Surface(color = MaterialTheme.colorScheme.onBackground) {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(48.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .offset(x = 25.dp),
                                text = "Админ панель",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center
                            )
                            IconButton(
                                modifier = Modifier.offset(x = 10.dp),
                                onClick = { viewModel.onEvent(AdminMainEvent.ShowSettingsEvent) }) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                navController.navigate(Route.AdminPatientsListScreen.route)
                            },
                            shape = RoundedCornerShape(BUTTON_CORNER_RADIUS),
                            colors = ButtonDefaults.buttonColors(contentColor = Color.White)
                        ) {
                            Text(text = "Список пациентов")
                        }
                    }
                }
            }

            if (state.settingsVisible) {
                ModalBottomSheet(containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    onDismissRequest = { viewModel.onEvent(AdminMainEvent.HideSettingsEvent) }) {
                    SettingsComposable(darkThemeChecked = viewModel.dataStoreHelper.getTheme()
                        .collectAsState(
                            initial = false
                        ).value, onDarkThemeOnChange = {
                        viewModel.onEvent(AdminMainEvent.SetThemeEvent(it))
                    }, onExitRowClick = {
                        viewModel.onEvent(AdminMainEvent.ExitEvent)
                        navController.navigate(Route.SignInScreen.route)
                    })
                }
            }
        }
    }
}