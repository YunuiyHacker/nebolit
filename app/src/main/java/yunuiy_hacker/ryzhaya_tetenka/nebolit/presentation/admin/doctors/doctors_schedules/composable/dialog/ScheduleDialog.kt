package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.doctors.doctors_schedules.composable.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Cabinet
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DoctorSchedule
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.BUTTON_CORNER_RADIUS
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.DIALOG_CORNER_RADIUS

@Composable
fun ScheduleDialog(
    modifier: Modifier = Modifier,
    schedule: DoctorSchedule,
    cabinets: List<Cabinet>,
    onDismissRequest: () -> Unit,
    onSaveSchedule: (hour: Int, minute: Int, cabinet: Cabinet) -> Unit,
    isDarkTheme: Boolean = true
) {
    val timePickerState = rememberTimePickerState(
        initialHour = if (schedule.time != null) schedule.time.hour else 0,
        initialMinute = if (schedule.time != null) schedule.time.minute else 0
    )
    var dropdownMenuExpanded by remember {
        mutableStateOf(false)
    }
    var selectedCabinet by remember {
        mutableStateOf(Cabinet())
    }

    Dialog(onDismissRequest = {}) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(DIALOG_CORNER_RADIUS))
                .background(
                    color = if (isDarkTheme) Color(0xFF131313) else Color(
                        0xFFFFFFFF
                    )
                )
        ) {
            Text(
                modifier = Modifier.padding(start = 24.dp, top = 24.dp),
                text = "Время",
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(12.dp))
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
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(12.dp)
                )
            ) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = if (selectedCabinet.id == 0) "Выберите кабинет" else selectedCabinet.title.toString(),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            DropdownMenu(
                modifier = Modifier,
                expanded = dropdownMenuExpanded,
                onDismissRequest = { dropdownMenuExpanded = false }) {
                cabinets.forEachIndexed { index, cabinet ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = cabinet.title.toString(),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        },
                        onClick = {
                            selectedCabinet = cabinet
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
                    onClick = { onDismissRequest() },
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
                        .padding(start = 12.dp, end = 24.dp, bottom = 24.dp),
                    onClick = {
                        onSaveSchedule(
                            timePickerState.hour,
                            timePickerState.minute,
                            selectedCabinet
                        )
                    },
                    shape = RoundedCornerShape(
                        BUTTON_CORNER_RADIUS
                    ),
                    enabled = selectedCabinet.id != 0,
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 24.dp)
                ) {
                    Text(
                        text = "Сохранить",
                        color = Color.White,
                        fontSize = 15.sp
                    )
                }

            }
        }
    }
}