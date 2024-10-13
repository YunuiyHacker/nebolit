package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User

class ReadUser(private val sharedPrefsHelper: SharedPrefsHelper) {

    operator fun invoke(): User? {
        return if (sharedPrefsHelper.user_id == null || sharedPrefsHelper.user_id == 0) null else User(
            id = sharedPrefsHelper.user_id,
            email = sharedPrefsHelper.email,
            passportId = sharedPrefsHelper.passport_id
        )
    }
}