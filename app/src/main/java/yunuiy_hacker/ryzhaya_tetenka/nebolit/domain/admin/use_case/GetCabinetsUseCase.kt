package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Cabinet

class GetCabinetsUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(): List<Cabinet> {
        return supabaseClient.postgrest.from("cabinets").select { }.decodeList<Cabinet>()
    }
}