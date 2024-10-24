package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Doctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.mappers.toDataDoctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.RegistrationDoctorModel

class RegistrationDoctorUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(
        registrationDoctorModel:
        RegistrationDoctorModel
    ): Doctor? {
        return supabaseClient.postgrest.from("doctors")
            .insert(registrationDoctorModel.toDataDoctor()) {
                select()
            }.decodeSingleOrNull<Doctor>()
    }
}