package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.doctors.doctors_schedules

import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.datetime.LocalTime
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Cabinet
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DoctorSchedule
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.AdminDoctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.date_picker.SelectableNonPastAndFuture24dDates
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.state.ContentState
import java.util.GregorianCalendar

class AdminDoctorsSchedulesState {
    var adminDoctor by mutableStateOf(AdminDoctor())

    var calendarList: MutableList<GregorianCalendar> = mutableStateListOf()

    var selectedCalendar by mutableStateOf(GregorianCalendar())
    var selectedDoctorSchedule by mutableStateOf(
        DoctorSchedule(
            time = LocalTime(
                hour = 0,
                minute = 0
            )
        )
    )
    var selectedCabinet by mutableStateOf(Cabinet())

    var doctorSchedules: MutableList<DoctorSchedule> = mutableStateListOf()
    var cabinets: MutableList<Cabinet> = mutableStateListOf()

    @OptIn(ExperimentalMaterial3Api::class)
    var appointmentDate by mutableStateOf(
        DatePickerState(
            CalendarLocale("ru"),
            selectableDates = SelectableNonPastAndFuture24dDates
        )
    )

    var scheduleIsEditState by mutableStateOf(false)

    var valid by mutableStateOf(true)

    var contentState by mutableStateOf(ContentState())
    var showScheduleDialog by mutableStateOf(false)
    var showDialog by mutableStateOf(false)
    var showAppointmentDataDialog by mutableStateOf(false)

    var success by mutableStateOf(false)
}