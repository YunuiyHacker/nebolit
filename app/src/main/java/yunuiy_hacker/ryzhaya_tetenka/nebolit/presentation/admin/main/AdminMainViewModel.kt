package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.main

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import javax.inject.Inject

@HiltViewModel
class AdminMainViewModel @Inject constructor(
    private val sharedPrefsHelper: SharedPrefsHelper,
    val dataStoreHelper: DataStoreHelper
) : ViewModel() {
    val state by mutableStateOf(AdminMainState())

    fun onEvent(event: AdminMainEvent) {
        when (event) {
            is AdminMainEvent.ShowSettingsEvent -> state.settingsVisible = true
            is AdminMainEvent.HideSettingsEvent -> state.settingsVisible = false

            is AdminMainEvent.SetThemeEvent -> setDarkTheme(event.isDarkTheme)
            is AdminMainEvent.ExitEvent -> exit()
        }
    }

    private fun setDarkTheme(isDarkTheme: Boolean) {
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        GlobalScope.launch {
            dataStoreHelper.setTheme(isDarkTheme)
        }
    }

    private fun exit() {
        sharedPrefsHelper.user_id = 0
        sharedPrefsHelper.email = ""
    }
}