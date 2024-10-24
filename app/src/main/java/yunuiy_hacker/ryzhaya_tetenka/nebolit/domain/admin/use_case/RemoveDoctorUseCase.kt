package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Passport
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User

class RemoveDoctorUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(email: String) {
        val user = supabaseClient.postgrest.from("users").delete {
            filter {
                User::email eq email
            }
        }.decodeSingleOrNull<User>()

        val passport = supabaseClient.postgrest.from("passports").delete {
            filter {
                User::passportId eq user?.passportId
            }
        }.decodeSingleOrNull<Passport>()

        val doctor = supabaseClient.postgrest.from("doctors").delete {
            filter {
                Patient::userId eq user?.id
            }
        }
    }
}