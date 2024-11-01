package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DoctorSchedule

class RemoveDoctorScheduleUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(doctorSchedule: DoctorSchedule) {
        supabaseClient.postgrest.from("doctor_schedules").delete {
            filter {
                DoctorSchedule::id eq doctorSchedule.id
            }
        }
    }
}