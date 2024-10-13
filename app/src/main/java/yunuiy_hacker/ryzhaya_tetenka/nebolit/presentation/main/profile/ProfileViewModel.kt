package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SaveReadPersonDataUseCase
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val saveReadPersonDataUseCase: SaveReadPersonDataUseCase) :
    ViewModel() {
    val state by mutableStateOf(ProfileState())

    init {
        val patient = saveReadPersonDataUseCase.readPatient.invoke()
        if (patient?.id!! > 0)
            state.isPatientAccount = true
        else
            state.isPatientAccount = false
    }
}