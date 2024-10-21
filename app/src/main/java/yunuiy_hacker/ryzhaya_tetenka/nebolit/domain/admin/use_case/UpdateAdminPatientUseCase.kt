package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Passport
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toDateOfMobileFormat
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toLocalDate
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.AdminPatient

class UpdateAdminPatientUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(adminPatient: AdminPatient): Boolean {
        val user: User? = supabaseClient.postgrest.from("users").update({
            User::email setTo adminPatient.email
            User::password setTo adminPatient.password
        }) {
            filter {
                User::id eq adminPatient.user_id
            }
            select()
        }.decodeSingleOrNull<User>()

        if (user != null) {
            val passport: Passport? = supabaseClient.postgrest.from("passports").update({
                Passport::surname setTo adminPatient.surname
                Passport::name setTo adminPatient.name
                Passport::lastname setTo adminPatient.lastname
                Passport::dateOfBirth setTo adminPatient.dateOfBirth.toDateOfMobileFormat()
                    .toLocalDate()
                Passport::addressOfBirth setTo adminPatient.addressOfBirth
                Passport::series setTo adminPatient.series
                Passport::code setTo adminPatient.code
                Passport::sex setTo adminPatient.sex
                Passport::issueDate setTo adminPatient.issueDate.toDateOfMobileFormat()
                    .toLocalDate()
                Passport::issueOrganization setTo adminPatient.issueOrganization
                Passport::departmentCode setTo adminPatient.departmentCode
            }) {
                filter {
                    Passport::id eq user.passportId
                }
                select()
            }.decodeSingleOrNull<Passport>()
            if (passport != null) {
                val patient: Patient? = supabaseClient.postgrest.from("patients").update({
                    Patient::registrationAddress setTo adminPatient.registrationAddress
                    Patient::liveAddress setTo adminPatient.liveAddress
                    Patient::insuranceCompany setTo adminPatient.insuranceCompany
                    Patient::policy setTo adminPatient.policy
                    Patient::height setTo adminPatient.height
                    Patient::weight setTo adminPatient.weight
                }) {
                    filter {
                        Patient::userId eq user.id
                    }
                    select()
                }.decodeSingleOrNull<Patient>()

                if (user != null)
                    return true
            }
        }
        return false
    }
}