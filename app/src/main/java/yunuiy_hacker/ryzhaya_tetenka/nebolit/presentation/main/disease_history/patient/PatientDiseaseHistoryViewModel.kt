package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.disease_history.patient

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.common.use_case.GetDiseasesHistoryUseCase
import javax.inject.Inject

@HiltViewModel
class PatientDiseaseHistoryViewModel @Inject constructor(
    val dataStoreHelper: DataStoreHelper,
    private val sharedPrefsHelper: SharedPrefsHelper,
    private val getDiseasesHistoryUseCase: GetDiseasesHistoryUseCase
) : ViewModel() {
    val state by mutableStateOf(PatientDiseaseHistoryState())

    fun onEvent(event: PatientDiseaseHistoryEvent) {
        when (event) {
            is PatientDiseaseHistoryEvent.GetDiseasesHistoryEvent -> getDiseasesHistory()

            is PatientDiseaseHistoryEvent.ShowDialogEvent -> state.showDialog = true
            is PatientDiseaseHistoryEvent.HideDialogEvent -> state.showDialog = false
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getDiseasesHistory() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {
                    state.diseasesHistories = getDiseasesHistoryUseCase.execute(
                        patientId = sharedPrefsHelper.patient_id
                    ).toMutableList()

                    state.contentState.isLoading.value = false
                } catch (e: Exception) {
                    state.contentState.isLoading.value = false
                    state.contentState.exception.value = e
                    state.showDialog = true
                }
            }
        }
    }
}