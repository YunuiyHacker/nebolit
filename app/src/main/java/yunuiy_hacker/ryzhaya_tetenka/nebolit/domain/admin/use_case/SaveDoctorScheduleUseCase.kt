package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DoctorSchedule

class SaveDoctorScheduleUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(doctorSchedule: DoctorSchedule): DoctorSchedule? {
        return supabaseClient.postgrest.from("doctor_schedules")
            .upsert(doctorSchedule) {
                select()
            }
            .decodeSingleOrNull<DoctorSchedule>()
    }
}