package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Doctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Passport
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User

class GetAllDoctorsUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(): MutableList<Doctor> {
        val mutableList: MutableList<Doctor> =
            supabaseClient.postgrest.from("doctors")
                .select(Columns.raw("id, user_id, users(id, email, password, passport_id, passports(surname, name, lastname, date_of_birth, address_of_birth, series, code, sex, issue_date, issue_organization, department_code)), specialization_id, specializations(id, title), licenze_number"))
                .decodeList<Doctor>().toMutableList()
        return mutableList
    }
}