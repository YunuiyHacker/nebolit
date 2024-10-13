package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Specialization

class GetSpecializationById(private val supabaseClient: SupabaseClient) {

    suspend operator fun invoke(id: Int): Specialization? {
        return supabaseClient.postgrest.from("specializations").select {
            filter { Specialization::id eq id }
        }.decodeSingleOrNull<Specialization>()
    }
}