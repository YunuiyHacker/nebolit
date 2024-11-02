package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.patient.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Appointment
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.dateToLocalDateString
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toDateOfMobileFormat
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toPassportDate
import java.util.GregorianCalendar

class GetPatientAppointmentsUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(patientId: Int): List<Appointment> {
        val calendar = GregorianCalendar()

        val result = supabaseClient.postgrest.from("appointments")
            .select(Columns.raw("id, patient_id, doctor_schedule_id, appointment_status_id, doctor_schedules(id, doctor_id, cabinet_id, time, date, doctors(id, users(id, passports(id, surname, name, lastname)), specializations(id, title)), cabinets(id, title))")) {
                filter {
                    Appointment::patientId eq patientId
                    gte("doctor_schedules.date", calendar.time.dateToLocalDateString())
                    Appointment::appointmentStatusId eq 1
                }
            }.decodeList<Appointment>().toMutableList()

        result.removeIf { it.doctorSchedule == null }
        result.removeIf {
            it.doctorSchedule?.date.toPassportDate()
                .toDateOfMobileFormat().time < calendar.time.time
        }
        result.removeIf { it.appointmentStatusId != 1 }

        return result.sortedWith(compareBy<Appointment>({ it.doctorSchedule?.date }).thenBy({ it.doctorSchedule?.time }))
    }
}