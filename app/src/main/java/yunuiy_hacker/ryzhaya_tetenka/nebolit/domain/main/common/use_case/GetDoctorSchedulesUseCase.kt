package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.common.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Order
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DoctorSchedule
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.dateToLocalDateString
import java.util.Date

class GetDoctorSchedulesUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(doctorId: Int, startDate: Date, endDate: Date): List<DoctorSchedule> {
        return supabaseClient.postgrest.from("doctor_schedules")
            .select(Columns.raw("id, doctor_id, cabinet_id, time, date, doctors(id, user_id, specialization_id, users(id, passport_id, passports(id, surname, name, lastname)), specializations(id, title)), cabinets(id, title), appointments(id, patient_id, doctor_schedule_id, appointment_status_id, patients(id, users(id, passports(id, surname, name, lastname, date_of_birth, sex))))")) {
                filter {
                    DoctorSchedule::doctorId eq doctorId
                    DoctorSchedule::date gte startDate.dateToLocalDateString()
                    DoctorSchedule::date lte endDate.dateToLocalDateString()
                }
                order("date", order = Order.ASCENDING)
                order("time", order = Order.ASCENDING)
            }.decodeList<DoctorSchedule>()
    }
}