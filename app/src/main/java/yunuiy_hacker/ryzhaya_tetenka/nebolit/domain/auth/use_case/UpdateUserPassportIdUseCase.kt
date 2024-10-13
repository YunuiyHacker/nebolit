package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Passport
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.UpdateUserPassportIdModel

class UpdateUserPassportIdUseCase(private val supabaseClient: SupabaseClient) {

    suspend operator fun invoke(updateUserPassportModel: UpdateUserPassportIdModel): User? {
        return supabaseClient.postgrest.from("users").update({
            User::passportId setTo updateUserPassportModel.passportId
        }) {
            filter {
                User::id eq updateUserPassportModel.userId
            }
            select()
        }.decodeSingleOrNull<User>()
    }
}