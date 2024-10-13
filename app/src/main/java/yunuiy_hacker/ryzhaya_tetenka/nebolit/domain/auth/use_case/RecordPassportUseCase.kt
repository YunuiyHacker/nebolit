package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Passport
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.mappers.toDataPassport
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.RecordPassportModel

class RecordPassportUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(recordPassportModel: RecordPassportModel): Passport? {
        return supabaseClient.postgrest.from("passports")
            .insert(recordPassportModel.toDataPassport()) {
                select()
            }.decodeSingleOrNull<Passport>()
    }
}