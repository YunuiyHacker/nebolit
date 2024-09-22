package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.net.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.mappers.toDataUser
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.SignUpModel

class SignUpUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(signUpModel: SignUpModel): User? {
        return supabaseClient.postgrest.from("users")
            .insert(value = signUpModel.toDataUser()) {
                select()
            }.decodeSingleOrNull<User>()
    }
}