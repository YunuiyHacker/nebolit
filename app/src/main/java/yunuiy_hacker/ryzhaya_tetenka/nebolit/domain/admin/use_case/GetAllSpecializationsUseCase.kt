package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Specialization

class GetAllSpecializationsUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(): List<Specialization> {
        return supabaseClient.postgrest.from("specializations").select {  }
            .decodeList<Specialization>()
    }
}