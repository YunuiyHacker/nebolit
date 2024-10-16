package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.patient.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Passport
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.dateToLocalDateString
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.dateToString
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toLocalDate
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.patient.model.UpdatePatientDataModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.patient.model.UpdatePatientDataResultModel

class UpdatePatientDataUseCase(
    private val supabaseClient: SupabaseClient, private val sharedPrefsHelper: SharedPrefsHelper
) {

    suspend fun execute(updatePatientDataModel: UpdatePatientDataModel): UpdatePatientDataResultModel? {
        val updatePatientDataResultModel = UpdatePatientDataResultModel()

        val m = updatePatientDataModel
        val s = sharedPrefsHelper

        if (m.surname == s.surname && m.name == s.name && m.lastname == s.lastname && m.dateOfBirth.dateToLocalDateString() == s.dateOfBirth && m.registrationAddress == s.registration_address && m.liveAddress == s.live_address && m.policy == s.policy && m.insuranceCompany == s.insurance_company && m.height == s.height && m.weight == s.weight) {

        } else {
            val passport: Passport? = supabaseClient.postgrest.from(
                "passports"
            ).update({
                Passport::surname setTo m.surname
                Passport::name setTo m.name
                Passport::lastname setTo m.lastname
                Passport::dateOfBirth setTo m.dateOfBirth.toLocalDate()
            }) {
                filter {
                    Passport::id eq s.passport_id
                }
                select()
            }.decodeSingleOrNull<Passport>()

            updatePatientDataResultModel.passport = passport

            if (passport != null) {
                val patient: Patient? = supabaseClient.postgrest.from("patients").update({
                    Patient::registrationAddress setTo m.registrationAddress
                    Patient::liveAddress setTo m.liveAddress
                    Patient::policy setTo m.policy
                    Patient::insuranceCompany setTo m.insuranceCompany
                    Patient::height setTo m.height
                    Patient::weight setTo m.weight
                }) {
                    filter {
                        Patient::id eq s.patient_id
                    }
                    select()
                }.decodeSingleOrNull<Patient>()

                if (patient != null)
                    updatePatientDataResultModel.patient = patient
            }
        }
        return updatePatientDataResultModel
    }

}