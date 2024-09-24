package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import io.github.jan.supabase.SupabaseClient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Doctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Specialization
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper

class ReadDoctor(private val sharedPrefsHelper: SharedPrefsHelper) {

    operator fun invoke(): Doctor? {
        return Doctor(
            id = sharedPrefsHelper.doctor_id,
            user_id = sharedPrefsHelper.user_id,
            specialization_id = sharedPrefsHelper.specialization_id,
            licenze_number = sharedPrefsHelper.licenze_number,
            user = User(
                id = sharedPrefsHelper.user_id,
                surname = sharedPrefsHelper.surname,
                name = sharedPrefsHelper.name,
                lastname = sharedPrefsHelper.lastname,
                email = sharedPrefsHelper.email
            ),
            specialization = Specialization(
                id = sharedPrefsHelper.specialization_id,
                title = sharedPrefsHelper.specialization_title!!
            )
        )
    }
}