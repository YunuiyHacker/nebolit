package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.enums.Role
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient

class DefineRole(private val supabaseClient: SupabaseClient) {

    suspend operator fun invoke(user_id: Int): Role {
        val patient: Patient? = supabaseClient.postgrest.from("patients").select {
            filter {
                Patient::user_id eq user_id
            }
        }.decodeSingleOrNull<Patient>()
        if (patient != null)
            return Role.Patient
        return Role.Doctor
    }
}