package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.patient.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Appointment

class GetPatientAppointmentsUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(patientId: Int): List<Appointment> {
        return supabaseClient.postgrest.from("appointments").select {
            filter {
                Appointment::patientId eq patientId
            }
        }.decodeList<Appointment>()
    }
}