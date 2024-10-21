package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.admin.patients.patients_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.mappers.toAdminPatients
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case.GetAllPatientsUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.use_case.RemovePatientUseCase
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AdminPatientsListViewModel @Inject constructor(
    val dataStoreHelper: DataStoreHelper,
    private val getAllPatientsUseCase: GetAllPatientsUseCase,
    private val removePatientUseCase: RemovePatientUseCase
) :
    ViewModel() {
    val state by mutableStateOf(AdminPatientsListState())

    init {
        state.contentState.isLoading.value = true
        GlobalScope.launch {
            state.patients = getAllPatientsUseCase.execute().toAdminPatients()
            state.contentState.isLoading.value = false
        }
    }

    fun onEvent(event: AdminPatientsListEvent) {
        when (event) {
            is AdminPatientsListEvent.ChangeSelectedPatientIndexEvent -> state.tableSelectedPatientIndex =
                event.index

            is AdminPatientsListEvent.DeletePatientEvent -> deletePatient()

            is AdminPatientsListEvent.ShowDialogEvent -> state.showDialog = true
            is AdminPatientsListEvent.HideDialogEvent -> state.showDialog = false
        }
    }

    fun deletePatient() {
        if (state.tableSelectedPatientIndex == -2) {
            state.contentState.data.value = "Выберите пациента"
            state.showDialog = true
        } else {
            state.contentState.isLoading.value = true

            val email = state.patients[state.tableSelectedPatientIndex].email
            GlobalScope.launch {
                runBlocking {
                    try {
                        removePatientUseCase.execute(email = email)

                        state.contentState.isLoading.value = false
                        state.patients.removeAt(state.tableSelectedPatientIndex)
                    } catch (e: Exception) {
                        state.contentState.isLoading.value = false
                    }
                }
            }
        }
    }
}