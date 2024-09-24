package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.mappers.toDataUser
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.SignUpModel

class CheckRegistrationByEmail(private val supabaseClient: SupabaseClient) {

    suspend operator fun invoke(signUpModel: SignUpModel): Boolean {
        val localUser: User? = supabaseClient.postgrest.from("users").select {
            filter {
                User::email eq signUpModel.toDataUser().email
            }
        }.decodeSingleOrNull<User>()

        return localUser == null
    }
}