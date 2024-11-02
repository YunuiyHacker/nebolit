package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.receive_patient

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DoctorSchedule
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.TreatmentResult
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.TypeOfPayment
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.VisitPurpose
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.state.ContentState

class ReceivePatientState {
    var doctorSchedule by mutableStateOf(DoctorSchedule())

    var typesOfPayment: MutableList<TypeOfPayment> = mutableStateListOf()
    var typeOfPayment by mutableStateOf(TypeOfPayment())
    var treatmentResults: MutableList<TreatmentResult> = mutableStateListOf()
    var treatmentResult by mutableStateOf(TreatmentResult())
    var visitPurposes: MutableList<VisitPurpose> = mutableStateListOf()
    var visitPurpose by mutableStateOf(VisitPurpose())

    var diagnosis by mutableStateOf("")

    var contentState by mutableStateOf(ContentState())
    var showDialog by mutableStateOf(false)

    var valid by mutableStateOf(false)

    var succes by mutableStateOf(false)
}