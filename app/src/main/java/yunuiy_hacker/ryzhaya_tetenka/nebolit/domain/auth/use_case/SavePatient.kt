package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient

class SavePatient(private val sharedPrefsHelper: SharedPrefsHelper) {

    operator fun invoke(patient: Patient) {
        sharedPrefsHelper.patient_id = patient.id!!
        sharedPrefsHelper.user_id = patient.userId!!
        sharedPrefsHelper.registration_address = patient.registrationAddress
        sharedPrefsHelper.live_address = patient.liveAddress
        sharedPrefsHelper.insurance_company = patient.insuranceCompany
        sharedPrefsHelper.policy = patient.policy
        sharedPrefsHelper.height = patient.height!!
        sharedPrefsHelper.weight = patient.weight!!
    }
}