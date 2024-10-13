package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.edit_person_data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SaveReadPersonDataUseCase
import javax.inject.Inject

@HiltViewModel
class EditPersonDataViewModel @Inject constructor(private val saveReadPersonDataUseCase: SaveReadPersonDataUseCase) :
    ViewModel() {
    val state by mutableStateOf(EditPersonDataState())

    init {
        if (saveReadPersonDataUseCase.readPatient.invoke() != null)
            state.isPatientUser = true
        else
            state.isPatientUser = false
    }
}