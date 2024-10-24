package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Doctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Passport
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toDateOfMobileFormat
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toLocalDate
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.AdminDoctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.AdminPatient

class UpdateAdminDoctorUseCase(private val supabaseClient: SupabaseClient) {

    suspend fun execute(adminDoctor: AdminDoctor): Boolean {
        val user: User? = supabaseClient.postgrest.from("users").update({
            User::email setTo adminDoctor.email
            User::password setTo adminDoctor.password
        }) {
            filter {
                User::id eq adminDoctor.user_id
            }
            select()
        }.decodeSingleOrNull<User>()

        if (user != null) {
            val passport: Passport? = supabaseClient.postgrest.from("passports").update({
                Passport::surname setTo adminDoctor.surname
                Passport::name setTo adminDoctor.name
                Passport::lastname setTo adminDoctor.lastname
                Passport::dateOfBirth setTo adminDoctor.dateOfBirth.toDateOfMobileFormat()
                    .toLocalDate()
                Passport::addressOfBirth setTo adminDoctor.addressOfBirth
                Passport::series setTo adminDoctor.series
                Passport::code setTo adminDoctor.code
                Passport::sex setTo adminDoctor.sex
                Passport::issueDate setTo adminDoctor.issueDate.toDateOfMobileFormat()
                    .toLocalDate()
                Passport::issueOrganization setTo adminDoctor.issueOrganization
                Passport::departmentCode setTo adminDoctor.departmentCode
            }) {
                filter {
                    Passport::id eq user.passportId
                }
                select()
            }.decodeSingleOrNull<Passport>()
            if (passport != null) {
                val doctor: Doctor? = supabaseClient.postgrest.from("doctors").update({
                    Doctor::specializationId setTo adminDoctor.specializationId
                    Doctor::licenzeNumber setTo adminDoctor.licenzeNumber
                }) {
                    filter {
                        Doctor::userId eq user.id
                    }
                    select()
                }.decodeSingleOrNull<Doctor>()

                if (user != null)
                    return true
            }
        }
        return false
    }
}