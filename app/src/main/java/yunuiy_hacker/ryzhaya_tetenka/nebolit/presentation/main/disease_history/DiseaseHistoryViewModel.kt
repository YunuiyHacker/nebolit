package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.disease_history

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import javax.inject.Inject

@HiltViewModel
class DiseaseHistoryViewModel @Inject constructor(val dataStoreHelper: DataStoreHelper) :
    ViewModel() {
    val state by mutableStateOf(DiseaseHistoryState())

    fun onEvent(event: DiseaseHistoryEvent) {

    }
}