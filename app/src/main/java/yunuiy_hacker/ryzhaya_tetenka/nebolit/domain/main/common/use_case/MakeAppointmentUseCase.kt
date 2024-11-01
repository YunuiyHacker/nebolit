package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.common.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Appointment

class MakeAppointmentUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(appointment: Appointment): Appointment? {
        return supabaseClient.postgrest.from("appointments").insert(appointment) {
            select()
        }.decodeSingleOrNull<Appointment>()
    }
}