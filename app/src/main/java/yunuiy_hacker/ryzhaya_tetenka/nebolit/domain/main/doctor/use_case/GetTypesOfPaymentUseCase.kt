package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.doctor.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.TypeOfPayment

class GetTypesOfPaymentUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(): List<TypeOfPayment> {
        return supabaseClient.postgrest.from("types_of_payment").select { }
            .decodeList<TypeOfPayment>()
    }
}