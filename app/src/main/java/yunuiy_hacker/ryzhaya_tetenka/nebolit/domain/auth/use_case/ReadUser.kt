package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User

class ReadUser(private val sharedPrefsHelper: SharedPrefsHelper) {

    operator fun invoke(): User? {
        return User(
            id = sharedPrefsHelper.user_id,
            surname = sharedPrefsHelper.surname,
            name = sharedPrefsHelper.name,
            lastname = sharedPrefsHelper.lastname,
            email = sharedPrefsHelper.email
        )
    }
}