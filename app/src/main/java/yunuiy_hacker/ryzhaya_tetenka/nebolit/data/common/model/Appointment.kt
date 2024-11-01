package yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Appointment(
    val id: Int = 0,
    @SerialName("patient_id")
    val patientId: Int = 0,
    @SerialName("doctor_schedules_id")
    val doctorId: Int = 0,
    @SerialName("appointment_status_id")
    val appointmentStatusId: Int = 0,
    @SerialName("appointment_date_and_time")
    val appointmentDateAndTime: LocalDate? = null,
    @SerialName("patients")
    val patient: Patient? = null,
    @SerialName("doctor_schedules")
    val doctorSchedule: DoctorSchedule? = null,
    @SerialName("appointment_statuses")
    val appointmentStatus: AppointmentStatus
)
