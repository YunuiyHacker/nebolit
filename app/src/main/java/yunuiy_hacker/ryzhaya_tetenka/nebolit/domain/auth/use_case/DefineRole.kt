package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.enums.Role
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Doctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient

class DefineRole(private val supabaseClient: SupabaseClient) {

    suspend operator fun invoke(user_id: Int): Role {
        val patient: Patient? = supabaseClient.postgrest.from("patients").select {
            filter {
                Patient::userId eq user_id
            }
        }.decodeSingleOrNull<Patient>()
        val doctor: Doctor? = supabaseClient.postgrest.from("doctors").select {
            filter {
                Doctor::userId eq user_id
            }
        }.decodeSingleOrNull<Doctor>()
        if (patient != null)
            return Role.Patient
        if (doctor != null)
            return Role.Doctor
        return Role.Admin
    }
}