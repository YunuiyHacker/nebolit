package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.doctors.doctors_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.mappers.toAdminDoctors
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case.GetAllDoctorsUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case.RemoveDoctorUseCase
import javax.inject.Inject

@HiltViewModel
class AdminDoctorsListViewModel @Inject constructor(
    val dataStoreHelper: DataStoreHelper,
    private val getAllDoctorsUseCase: GetAllDoctorsUseCase,
    private val removeDoctorUseCase: RemoveDoctorUseCase
) :
    ViewModel() {
    val state by mutableStateOf(AdminDoctorsListState())

    init {
        state.contentState.isLoading.value = true
        GlobalScope.launch {
            state.doctors = getAllDoctorsUseCase.execute().toAdminDoctors()
            state.contentState.isLoading.value = false
        }
    }

    fun onEvent(event: AdminDoctorsListEvent) {
        when (event) {
            is AdminDoctorsListEvent.ChangeSelectedDoctorIndexEvent -> state.tableSelectedDoctorIndex =
                event.index

            is AdminDoctorsListEvent.DeleteDoctorEvent -> deleteDoctor()

            is AdminDoctorsListEvent.ShowDialogEvent -> state.showDialog = true
            is AdminDoctorsListEvent.HideDialogEvent -> state.showDialog = false
        }
    }

    fun deleteDoctor() {
        if (state.tableSelectedDoctorIndex == -2) {
            state.contentState.data.value = "Выберите врача"
            state.showDialog = true
        } else {
            state.contentState.isLoading.value = true

            val email = state.doctors[state.tableSelectedDoctorIndex].email
            GlobalScope.launch {
                runBlocking {
                    try {
                        removeDoctorUseCase.execute(email = email)

                        state.contentState.isLoading.value = false
                        state.doctors.removeAt(state.tableSelectedDoctorIndex)
                    } catch (e: Exception) {
                        state.contentState.isLoading.value = false
                    }
                }
            }
        }
    }
}