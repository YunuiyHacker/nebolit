package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.doctor.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.VisitPurpose

class GetVisitPurposesUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(): List<VisitPurpose> {
        return supabaseClient.postgrest.from("visit_purposes").select { }
            .decodeList<VisitPurpose>()
    }
}