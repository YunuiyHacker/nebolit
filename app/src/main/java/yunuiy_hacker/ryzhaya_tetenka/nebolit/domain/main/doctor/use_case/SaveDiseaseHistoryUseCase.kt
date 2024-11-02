package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.doctor.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Appointment
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DiseaseHistory

class SaveDiseaseHistoryUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(appointmentId: Int, diseaseHistory: DiseaseHistory): DiseaseHistory? {
        val result = supabaseClient.postgrest.from("diseases_history").insert(diseaseHistory) {
            select()
        }.decodeSingleOrNull<DiseaseHistory>()

        if (result != null) {
            val appointment = supabaseClient.postgrest.from("appointments").update({
                Appointment::appointmentStatusId setTo 2
            }) {
                filter { Appointment::id eq appointmentId }
                select()
            }.decodeSingleOrNull<Appointment>()

            if (appointment != null) {
                return result
            } else throw Exception("При сохранении информации о посещении произошла ошибка")
        } else throw Exception("При сохранении информации о посещении произошла ошибка")
    }
}