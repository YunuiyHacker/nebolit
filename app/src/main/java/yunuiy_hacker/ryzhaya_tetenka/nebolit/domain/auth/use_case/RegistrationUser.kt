package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.mappers.toDataUser
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.SignUpModel

class RegistrationUser(private val supabaseClient: SupabaseClient) {

    suspend operator fun invoke(signUpModel: SignUpModel): User? {
        return supabaseClient.postgrest.from("users").insert(signUpModel.toDataUser()) {
            select()
        }.decodeSingleOrNull<User>()
    }
}