package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User

class SaveUser(private val sharedPrefsHelper: SharedPrefsHelper) {

    operator fun invoke(user: User) {
        sharedPrefsHelper.user_id = user.id!!
        sharedPrefsHelper.surname = user.surname
        sharedPrefsHelper.name = user.name
        sharedPrefsHelper.lastname = user.lastname
        sharedPrefsHelper.email = user.email
    }
}