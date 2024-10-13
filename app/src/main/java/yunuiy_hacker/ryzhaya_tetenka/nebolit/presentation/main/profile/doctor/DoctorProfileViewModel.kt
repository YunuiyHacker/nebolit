package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.profile.doctor

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SaveReadPersonDataUseCase
import javax.inject.Inject

@HiltViewModel
class DoctorProfileViewModel @Inject constructor(
    private val saveReadPersonDataUseCase: SaveReadPersonDataUseCase,
    private val sharedPrefsHelper: SharedPrefsHelper,
    val dataStoreHelper: DataStoreHelper
) :
    ViewModel() {
    val state by mutableStateOf(DoctorProfileState())

    init {
        state.doctor = saveReadPersonDataUseCase.readDoctor.invoke()!!
    }

    fun onEvent(event: DoctorProfileEvent) {
        when (event) {
            is DoctorProfileEvent.ShowPassportEvent -> state.passportVisible = true
            is DoctorProfileEvent.HidePassportEvent -> state.passportVisible = false

            is DoctorProfileEvent.ShowSettingsEvent -> state.settingsVisible = true
            is DoctorProfileEvent.HideSettingsEvent -> state.settingsVisible = false

            is DoctorProfileEvent.SetThemeEvent -> setDarkTheme(event.isDarkTheme)
            is DoctorProfileEvent.ExitEvent -> exit()
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
        sharedPrefsHelper.passport_id = 0
        sharedPrefsHelper.surname = null
        sharedPrefsHelper.name = null
        sharedPrefsHelper.lastname = null
        sharedPrefsHelper.dateOfBirth = null
        sharedPrefsHelper.addressOfBirth = null
        sharedPrefsHelper.series = 0
        sharedPrefsHelper.code = 0
        sharedPrefsHelper.issueDate = null
        sharedPrefsHelper.issueOrganization = null
        sharedPrefsHelper.departmentCode = null

        sharedPrefsHelper.user_id = 0
        sharedPrefsHelper.email = ""

        sharedPrefsHelper.doctor_id = 0
        sharedPrefsHelper.specialization_id = 0
        sharedPrefsHelper.specialization_title = ""
        sharedPrefsHelper.licenze_number = ""
    }
}