package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.edit_person_data.patient

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.edit_person_data.EditPersonDataState
import javax.inject.Inject

@HiltViewModel
class EditPersonDataPatientViewModel @Inject constructor() : ViewModel() {
    val state by mutableStateOf(EditPersonDataState())

    fun onEvent() {

    }
}