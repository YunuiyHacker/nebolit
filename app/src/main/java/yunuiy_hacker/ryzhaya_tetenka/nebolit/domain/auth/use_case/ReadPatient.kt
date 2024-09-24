package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toLocalDate

class ReadPatient(private val sharedPrefsHelper: SharedPrefsHelper) {

    operator fun invoke(): Patient? {
        return Patient(
            id = sharedPrefsHelper.patient_id,
            user_id = sharedPrefsHelper.user_id,
            date_of_birth = sharedPrefsHelper.date_of_birth?.toLocalDate(),
            sex = sharedPrefsHelper.sex,
            registration_address = sharedPrefsHelper.registration_address,
            live_address = sharedPrefsHelper.live_address,
            insurance_company = sharedPrefsHelper.insurance_company,
            policy = sharedPrefsHelper.policy,
            height = sharedPrefsHelper.height,
            weight = sharedPrefsHelper.weight,
            user = User(
                id = sharedPrefsHelper.user_id,
                surname = sharedPrefsHelper.surname,
                name = sharedPrefsHelper.name,
                lastname = sharedPrefsHelper.lastname,
                email = sharedPrefsHelper.email
            )
        )
    }
}