package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.common.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DiseaseHistory

class GetDiseasesHistoryUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(patientId: Int): List<DiseaseHistory> {
        return supabaseClient.postgrest.from("diseases_history")
            .select(Columns.raw("id, doctor_id, type_of_payment_id, treatment_result_id, patient_id, visit_purpose_id, doctors(id, users(id, passports(id, surname, name, lastname)), specializations(id, title))), treatment_results(id, title), patients(id, users(id, passports(id, surname, name, lastname))), visit_purposes(id, title)")) {
                filter {
                    DiseaseHistory::patientId eq patientId
                }
            }.decodeList<DiseaseHistory>()
    }
}