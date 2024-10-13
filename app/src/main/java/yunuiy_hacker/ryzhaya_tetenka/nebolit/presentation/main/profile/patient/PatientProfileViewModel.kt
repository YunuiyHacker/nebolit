package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.profile.patient

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.collectAsState
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
class PatientProfileViewModel @Inject constructor(
    private val saveReadPersonDataUseCase: SaveReadPersonDataUseCase,
    private val sharedPrefsHelper: SharedPrefsHelper,
    val dataStoreHelper: DataStoreHelper
) :
    ViewModel() {
    val state by mutableStateOf(PatientProfileState())

    init {
        state.patient = saveReadPersonDataUseCase.readPatient.invoke()!!
    }

    fun onEvent(event: PatientProfileEvent) {
        when (event) {
            is PatientProfileEvent.ShowPassportEvent -> state.passportVisible = true
            is PatientProfileEvent.HidePassportEvent -> state.passportVisible = false
            is PatientProfileEvent.MoreInformationVisibilityToggleEvent -> state.moreInformationVisible =
                !state.moreInformationVisible

            is PatientProfileEvent.ShowSettingsEvent -> state.settingsVisible = true
            is PatientProfileEvent.HideSettingsEvent -> state.settingsVisible = false

            is PatientProfileEvent.SetThemeEvent -> setDarkTheme(event.isDarkTheme)
            is PatientProfileEvent.ExitEvent -> exit()
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

        sharedPrefsHelper.patient_id = 0
        sharedPrefsHelper.registration_address = ""
        sharedPrefsHelper.live_address = ""
        sharedPrefsHelper.insurance_company = ""
        sharedPrefsHelper.policy = ""
        sharedPrefsHelper.height = 0
        sharedPrefsHelper.weight = 0.0f
    }

}