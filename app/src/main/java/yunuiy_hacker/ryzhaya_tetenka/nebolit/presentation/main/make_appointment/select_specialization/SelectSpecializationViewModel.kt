package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.make_appointment.select_specialization

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case.GetAllSpecializationsUseCase
import javax.inject.Inject

@HiltViewModel
class SelectSpecializationViewModel @Inject constructor(
    val dataStoreHelper: DataStoreHelper,
    private val getAllSpecializationsUseCase: GetAllSpecializationsUseCase
) :
    ViewModel() {
    val state by mutableStateOf(SelectSpecializationState())

    fun onEvent(event: SelectSpecializationEvent) {
        when (event) {
            is SelectSpecializationEvent.GetAllSpecializationsEvent -> getAllSpecializations()
            is SelectSpecializationEvent.SelectCurrentSpecializationEvent -> {
                state.specialization = event.specialization
            }

            is SelectSpecializationEvent.ShowDialogEvent -> state.showDialog = true
            is SelectSpecializationEvent.HideDialogEvent -> state.showDialog = false
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getAllSpecializations() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {
                    state.specializations = getAllSpecializationsUseCase.execute().toMutableList()

                    state.contentState.isLoading.value = false
                } catch (e: Exception) {
                    state.contentState.isLoading.value = false
                }
            }
        }
    }

}