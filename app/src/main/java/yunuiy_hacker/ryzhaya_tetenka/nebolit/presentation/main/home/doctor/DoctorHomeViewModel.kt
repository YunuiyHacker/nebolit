package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.home.doctor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoctorHomeViewModel @Inject constructor() : ViewModel() {
    val state by mutableStateOf(DoctorHomeState())

    fun onEvent(event: DoctorHomeEvent) {

    }
}