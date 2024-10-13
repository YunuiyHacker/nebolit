package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Passport

class GetPassportById(private val supabaseClient: SupabaseClient) {

    suspend operator fun invoke(id: Int): Passport? {
        return supabaseClient.postgrest.from("passports").select {
            filter { Passport::id eq id }
        }.decodeSingleOrNull<Passport>()
    }
}