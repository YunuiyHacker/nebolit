package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.doctor.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.TreatmentResult

class GetTreatmentResultsUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(): List<TreatmentResult> {
        return supabaseClient.postgrest.from("treatment_results").select { }
            .decodeList<TreatmentResult>()
    }
}