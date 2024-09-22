package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.selects.select
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.net.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.RegistrationPatientModel

class RegistrationPatientUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(registrationPatientModel: RegistrationPatientModel): Patient? {
        return supabaseClient.postgrest.from("patients").insert(registrationPatientModel) {
            select()
        }.decodeSingleOrNull<Patient>()
    }
}