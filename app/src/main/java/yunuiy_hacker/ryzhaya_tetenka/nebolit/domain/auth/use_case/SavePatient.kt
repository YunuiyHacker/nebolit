package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toString

class SavePatient(private val sharedPrefsHelper: SharedPrefsHelper) {

    operator fun invoke(patient: Patient) {
        sharedPrefsHelper.patient_id = patient.id!!
        sharedPrefsHelper.user_id = patient.user_id!!
        sharedPrefsHelper.date_of_birth = patient.date_of_birth.toString()
        sharedPrefsHelper.sex = patient.sex!!
        sharedPrefsHelper.registration_address = patient.registration_address
        sharedPrefsHelper.live_address = patient.live_address
        sharedPrefsHelper.insurance_company = patient.insurance_company
        sharedPrefsHelper.policy = patient.policy
        sharedPrefsHelper.height = patient.height!!
        sharedPrefsHelper.weight = patient.weight!!
    }
}