package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.onboarding

import android.provider.Settings.Global
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(val dataStoreHelper: DataStoreHelper) :
    ViewModel() {

    fun onEvent(event: OnboardingEvent) {
        when (event) {
            is OnboardingEvent.SaveAppEntry -> saveAppEntry()
        }
    }

    private fun saveAppEntry() {
        GlobalScope.launch {
            dataStoreHelper.setAppEntry()
        }
    }
}