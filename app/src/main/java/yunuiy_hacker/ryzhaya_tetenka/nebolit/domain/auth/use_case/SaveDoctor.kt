package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Doctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper

class SaveDoctor(private val sharedPrefsHelper: SharedPrefsHelper) {

    operator fun invoke(doctor: Doctor) {
        sharedPrefsHelper.doctor_id = doctor.id!!
        sharedPrefsHelper.specialization_id = doctor.specializationId!!
        sharedPrefsHelper.specialization_title = doctor.specialization?.title
        sharedPrefsHelper.licenze_number = doctor.licenzeNumber
    }
}