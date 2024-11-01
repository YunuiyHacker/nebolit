package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.make_appointment.select_time

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import javax.inject.Inject

@HiltViewModel
class SelectTimeViewModel @Inject constructor(
    val dataStoreHelper: DataStoreHelper,
) :
    ViewModel() {
    val state by mutableStateOf(SelectTimeState())

    fun onEvent(event: SelectTimeEvent) {
        when (event) {
            is SelectTimeEvent.GetAllSpecializationsEvent -> getAllSpecializations()
            is SelectTimeEvent.SelectCurrentSpecializationEvent -> {
//                state.specialization = event.specialization
            }

            is SelectTimeEvent.ShowDialogEvent -> state.showDialog = true
            is SelectTimeEvent.HideDialogEvent -> state.showDialog = false
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getAllSpecializations() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {


                    state.contentState.isLoading.value = false
                } catch (e: Exception) {
                    state.contentState.isLoading.value = false
                }
            }
        }
    }

}