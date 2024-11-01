package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DoctorSchedule

class UpdateDoctorScheduleUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(doctorSchedule: DoctorSchedule): DoctorSchedule? {
        return supabaseClient.postgrest.from("doctor_schedules").update({
            DoctorSchedule::doctorId setTo doctorSchedule.doctorId
            DoctorSchedule::date setTo doctorSchedule.date
            DoctorSchedule::time setTo doctorSchedule.time
            DoctorSchedule::cabinetId setTo doctorSchedule.cabinetId
        }) {
            filter {
                DoctorSchedule::id eq doctorSchedule.id
            }
            select()
        }.decodeSingleOrNull<DoctorSchedule>()
    }
}