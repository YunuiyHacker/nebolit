package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DoctorSchedule
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.dateToLocalDateString
import java.util.Date

class GetAdminDoctorSchedulesUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(doctorId: Int, startDate: Date, endDate: Date): List<DoctorSchedule> {
        return supabaseClient.postgrest.from("doctor_schedules").select {
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