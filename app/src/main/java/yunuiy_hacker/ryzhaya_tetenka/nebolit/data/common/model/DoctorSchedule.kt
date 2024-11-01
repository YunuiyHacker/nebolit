package yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DoctorSchedule(
    val id: Int? = 0,
    @SerialName("doctor_id")
    val doctorId: Int? = 0,
    @SerialName("cabinet_id")
    val cabinetId: Int? = 0,
    val time: LocalTime? = null,
    val date: LocalDate? = null,
    @SerialName("doctors")
    val doctor: Doctor? = null,
    @SerialName("cabinets")
    val cabinet: Cabinet? = null
)
