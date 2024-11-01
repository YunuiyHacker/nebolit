package yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Appointment(
    val id: Int = 0,
    @SerialName("patient_id")
    val patientId: Int = 0,
    @SerialName("doctor_schedule_id")
    val doctorScheduleId: Int = 0,
    @SerialName("appointment_status_id")
    val appointmentStatusId: Int = 0,
    @SerialName("patients")
    val patient: Patient? = null,
    @SerialName("doctor_schedules")
    val doctorSchedule: DoctorSchedule? = null,
    @SerialName("appointment_statuses")
    val appointmentStatus: AppointmentStatus? = null
)
