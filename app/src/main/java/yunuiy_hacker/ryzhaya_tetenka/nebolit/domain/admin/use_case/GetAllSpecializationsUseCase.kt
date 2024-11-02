package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Specialization

class GetAllSpecializationsUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(sex: Boolean? = null): List<Specialization> {
        val result =
            supabaseClient.postgrest.from("specializations").select {}.decodeList<Specialization>()
                .toMutableList()

        if (sex != null) {
            result.removeIf {
                if (sex) {
                    it.id == 1 || it.id == 7
                } else {
                    it.id == 40 || it.id == 41
                }
            }
        }

        return result
    }
}