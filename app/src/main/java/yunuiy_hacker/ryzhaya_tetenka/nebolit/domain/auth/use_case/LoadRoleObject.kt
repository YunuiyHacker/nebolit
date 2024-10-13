package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.enums.Role
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Doctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.parent.RoleObject
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.LoadRoleObjectModel

class LoadRoleObject(private val supabaseClient: SupabaseClient) {

    suspend operator fun invoke(loadRoleObjectModel: LoadRoleObjectModel): RoleObject? {
        return if (loadRoleObjectModel.role == Role.Patient) supabaseClient.postgrest.from("patients")
            .select {
                filter {
                    Patient::userId eq loadRoleObjectModel.user_id
                }
            }.decodeSingleOrNull<Patient>()
        else
            supabaseClient.postgrest.from("doctors").select {
                filter {
                    Doctor::userId eq loadRoleObjectModel.user_id
                }
            }.decodeSingleOrNull<Doctor>()
    }
}