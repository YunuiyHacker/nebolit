package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Passport
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toLocalDate

class ReadPatient(private val sharedPrefsHelper: SharedPrefsHelper) {

    operator fun invoke(): Patient? {
        return Patient(
            id = sharedPrefsHelper.patient_id,
            userId = sharedPrefsHelper.user_id,
            registrationAddress = sharedPrefsHelper.registration_address,
            liveAddress = sharedPrefsHelper.live_address,
            insuranceCompany = sharedPrefsHelper.insurance_company,
            policy = sharedPrefsHelper.policy,
            height = sharedPrefsHelper.height,
            weight = sharedPrefsHelper.weight,
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
            )
        )
    }
}