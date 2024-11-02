package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.receive_patient

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DiseaseHistory
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toLocalDate
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.doctor.use_case.GetTreatmentResultsUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.doctor.use_case.GetTypesOfPaymentUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.doctor.use_case.GetVisitPurposesUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.doctor.use_case.SaveDiseaseHistoryUseCase
import java.util.GregorianCalendar
import javax.inject.Inject

@HiltViewModel
class ReceivePatientViewModel @Inject constructor(
    val dataStoreHelper: DataStoreHelper,
    private val sharedPrefsHelper: SharedPrefsHelper,
    private val getTypesOfPaymentUseCase: GetTypesOfPaymentUseCase,
    private val getTreatmentResultsUseCase: GetTreatmentResultsUseCase,
    private val getVisitPurposesUseCase: GetVisitPurposesUseCase,
    private val saveDiseaseHistoryUseCase: SaveDiseaseHistoryUseCase
) : ViewModel() {
    val state by mutableStateOf(ReceivePatientState())

    fun onEvent(event: ReceivePatientEvent) {
        when (event) {
            is ReceivePatientEvent.GetNeededData -> getNeededData()
            is ReceivePatientEvent.SelectTypeOfPayment -> {
                state.typeOfPayment = event.typeOfPayment
                validate()
            }

            is ReceivePatientEvent.SelectTreatmentResult -> {
                state.treatmentResult = event.treatmentResult
                validate()
            }

            is ReceivePatientEvent.SelectVisitPurpose -> {
                state.visitPurpose = event.visitPurpose
                validate()
            }

            is ReceivePatientEvent.ChangeDiagnosisEvent -> {
                state.diagnosis = event.diagnosis
                validate()
            }

            is ReceivePatientEvent.SaveDiseaseHistoryEvent -> saveDiseaseHistory()

            is ReceivePatientEvent.ShowDialogEvent -> state.showDialog = true
            is ReceivePatientEvent.HideDialogEvent -> state.showDialog = false
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getNeededData() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {
                    state.typesOfPayment = getTypesOfPaymentUseCase.execute().toMutableList()
                    state.treatmentResults = getTreatmentResultsUseCase.execute().toMutableList()
                    state.visitPurposes = getVisitPurposesUseCase.execute().toMutableList()

                    if (state.typesOfPayment.isNotEmpty()) state.typeOfPayment =
                        state.typesOfPayment.get(0)

                    if (state.treatmentResults.isEmpty()) state.treatmentResult =
                        state.treatmentResults.get(0)

                    if (state.visitPurposes.isNotEmpty()) state.visitPurpose =
                        state.visitPurposes.get(0)

                    state.contentState.isLoading.value = false
                } catch (e: Exception) {
                    state.contentState.isLoading.value = false
                    state.contentState.exception.value = e
                    state.showDialog = true
                }
            }

        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun saveDiseaseHistory() {
        state.contentState.isLoading.value = true

        GlobalScope.launch {
            runBlocking {
                try {
                    val calendar = GregorianCalendar()

                    val diseaseHistory: DiseaseHistory? = saveDiseaseHistoryUseCase.execute(
                        appointmentId = state.doctorSchedule.appointments[0].id,
                        diseaseHistory = DiseaseHistory(
                            doctorId = sharedPrefsHelper.doctor_id,
                            patientId = state.doctorSchedule.appointments[0].patientId,
                            typeOfPaymentId = state.typeOfPayment.id,
                            visitPurposeId = state.visitPurpose.id,
                            diagnosis = state.diagnosis,
                            treatmentResultId = if (state.visitPurpose.id == 1) null else state.treatmentResult.id,
                            date = calendar.time.toLocalDate()
                        )
                    )

                    if (diseaseHistory != null) throw Exception("Прием успешно закончен")
                    else {
                        state.succes = true
                        throw Exception("При сохранении информации о приеме произошла ошибка")
                    }

                    state.contentState.isLoading.value = false
                } catch (e: Exception) {
                    state.contentState.isLoading.value = false
                    state.contentState.exception.value = e
                    state.showDialog = true
                }
            }
        }
    }

    private fun validate() {
        if (state.diagnosis.isNotEmpty()) if (state.typeOfPayment.id != 0) if (state.visitPurpose.id != 0) if (state.visitPurpose.id == 1) state.valid =
            true
        else {
            if (state.treatmentResult.id != 0) state.valid = true
        }
        else state.valid = false
        else state.valid = false
        else state.valid = false
    }
}