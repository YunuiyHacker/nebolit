package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.disease_history.doctor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DiseaseHistory
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.state.ContentState

class DoctorDiseaseHistoryState {
    var patientId by mutableStateOf(0)
    var diseasesHistories: MutableList<DiseaseHistory> = mutableStateListOf()

    var contentState by mutableStateOf(ContentState())
    var showDialog by mutableStateOf(false)
}