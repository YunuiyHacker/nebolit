package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.doctor.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient

class GetPatientInfoUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(patientId: Int): Patient? {
        return supabaseClient.postgrest.from("patients")
            .select(Columns.raw("id, users(id, passports(id, surname, name, lastname, date_of_birth, sex))")) {
                filter {
                    Patient::id eq patientId
                }
            }.decodeSingleOrNull<Patient>()
    }
}