package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.net.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.SignInModel
import kotlin.math.sign

class SignInUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(signInModel: SignInModel): User? {
        return supabaseClient.postgrest.from("users").select {
            filter {
                User::email eq signInModel.email
                User::password eq signInModel.password
            }
        }.decodeSingleOrNull<User>()
    }
}