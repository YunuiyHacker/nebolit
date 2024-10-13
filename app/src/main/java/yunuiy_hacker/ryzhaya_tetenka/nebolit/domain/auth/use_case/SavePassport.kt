package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Passport
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toString

class SavePassport(private val sharedPrefsHelper: SharedPrefsHelper) {

    operator fun invoke(passport: Passport) {
        sharedPrefsHelper.passport_id = passport.id!!
        sharedPrefsHelper.surname = passport.surname
        sharedPrefsHelper.name = passport.name
        sharedPrefsHelper.lastname = passport.lastname
        sharedPrefsHelper.dateOfBirth = passport.dateOfBirth.toString()
        sharedPrefsHelper.addressOfBirth = passport.addressOfBirth
        sharedPrefsHelper.series = passport.series!!
        sharedPrefsHelper.code = passport.code!!
        sharedPrefsHelper.sex = passport.sex!!
        sharedPrefsHelper.issueDate = passport.issueDate.toString()
        sharedPrefsHelper.issueOrganization = passport.issueOrganization
        sharedPrefsHelper.departmentCode = passport.departmentCode
    }
}