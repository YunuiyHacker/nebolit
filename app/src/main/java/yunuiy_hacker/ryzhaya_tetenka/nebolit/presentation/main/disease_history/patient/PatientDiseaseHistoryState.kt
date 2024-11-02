package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.disease_history.patient

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DiseaseHistory
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.state.ContentState

class PatientDiseaseHistoryState {
    var diseasesHistories: MutableList<DiseaseHistory> = mutableStateListOf()

    var contentState by mutableStateOf(ContentState())
    var showDialog by mutableStateOf(false)
}