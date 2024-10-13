package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Passport
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toLocalDate

class ReadPassport(private val sharedPrefsHelper: SharedPrefsHelper) {

    operator fun invoke(): Passport? {
        return Passport(
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
    }
}