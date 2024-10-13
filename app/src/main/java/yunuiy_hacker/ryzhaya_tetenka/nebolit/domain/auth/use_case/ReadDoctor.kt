package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Doctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Passport
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Specialization
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toLocalDate

class ReadDoctor(private val sharedPrefsHelper: SharedPrefsHelper) {

    operator fun invoke(): Doctor? {
        return Doctor(
            id = sharedPrefsHelper.doctor_id,
            userId = sharedPrefsHelper.user_id,
            specializationId = sharedPrefsHelper.specialization_id,
            licenzeNumber = sharedPrefsHelper.licenze_number,
            user = User(
                id = sharedPrefsHelper.user_id,
                email = sharedPrefsHelper.email,
                passportId = sharedPrefsHelper.passport_id,
                passport = Passport(
                    id = sharedPrefsHelper.passport_id,
                    surname = sharedPrefsHelper.surname,
                    name = sharedPrefsHelper.name,
                    lastname = sharedPrefsHelper.lastname,
                    dateOfBirth = sharedPrefsHelper.dateOfBirth?.toLocalDate(),
                    addressOfBirth = sharedPrefsHelper.addressOfBirth,
                    series = sharedPrefsHelper.series,
                    code = sharedPrefsHelper.code,
                    sex = sharedPrefsHelper.sex,
                    issueDate = sharedPrefsHelper.issueDate?.toLocalDate(),
                    issueOrganization = sharedPrefsHelper.issueOrganization,
                    departmentCode = sharedPrefsHelper.departmentCode
                )
            ),
            specialization = Specialization(
                id = sharedPrefsHelper.specialization_id,
                title = sharedPrefsHelper.specialization_title!!
            )
        )
    }
}