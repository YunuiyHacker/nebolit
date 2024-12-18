package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DoctorSchedule
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toPassportDate

@Composable
fun PatientScheduleTable(
    modifier: Modifier = Modifier,
    doctorSchedules: List<DoctorSchedule>,
    onChangeSelected: (doctorSchedule: DoctorSchedule) -> Unit
) {
    val dates = remember { generateDatesForTwoWeeks() }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .clip(RoundedCornerShape(10.dp))
            .border(
                BorderStroke(width = 3.dp, color = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(10.dp)
            )
            .width(200 * 14.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
            ) {
                for (date in dates) {
                    Surface(
                        modifier = Modifier.weight(1f),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        color = MaterialTheme.colorScheme.onBackground
                    ) {
                        Text(
                            text = date.toPassportDate(),
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
            ) {
                for (date in dates) {
                    val schedules = doctorSchedules.filter { it.date == date }
                    Box(
                        modifier = Modifier.weight(1f), contentAlignment = Alignment.Center
                    ) {
                        if (schedules.isNotEmpty()) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                schedules.forEach { schedule ->
                                    Row(modifier = if (schedule.appointments.isEmpty()) Modifier
                                        .border(
                                            BorderStroke(
                                                1.dp, MaterialTheme.colorScheme.primary
                                            )
                                        )
                                        .background(
                                            color = MaterialTheme.colorScheme.primary.copy(
                                                0.77f
                                            )
                                        )
                                        .clickable {
                                            onChangeSelected(schedule)
                                        }
                                    else {
                                        Modifier
                                            .border(
                                                BorderStroke(
                                                    1.dp, MaterialTheme.colorScheme.primary
                                                )
                                            )
                                            .background(
                                                color = MaterialTheme.colorScheme.surface
                                            )
                                    }) {
                                        Text(
                                            text = schedule.time.toString(),
                                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = if (schedule.appointments.isEmpty()) 1.0f else 0.5f),
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(vertical = 10.dp)
                                                .fillMaxSize()

                                        )
                                    }
                                }
                            }
                        } else {
                            Row(
                                modifier = Modifier
                                    .border(
                                        BorderStroke(
                                            1.dp, MaterialTheme.colorScheme.primary
                                        )
                                    )
                                    .background(
                                        color = MaterialTheme.colorScheme.surface
                                    )
                            ) {
                                Text(
                                    text = "Нет записи",
                                    color = MaterialTheme.colorScheme.onSurface.copy(0.5f),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .padding(vertical = 10.dp)
                                        .fillMaxSize()

                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

