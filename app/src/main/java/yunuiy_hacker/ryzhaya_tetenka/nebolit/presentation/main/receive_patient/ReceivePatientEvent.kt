package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.receive_patient

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.TreatmentResult
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.TypeOfPayment
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.VisitPurpose

sealed class ReceivePatientEvent {
    data object GetNeededData : ReceivePatientEvent()
    data class SelectTypeOfPayment(val typeOfPayment: TypeOfPayment) : ReceivePatientEvent()
    data class SelectTreatmentResult(val treatmentResult: TreatmentResult) : ReceivePatientEvent()
    data class SelectVisitPurpose(val visitPurpose: VisitPurpose) : ReceivePatientEvent()

    data class ChangeDiagnosisEvent(val diagnosis: String) : ReceivePatientEvent()
    data object SaveDiseaseHistoryEvent : ReceivePatientEvent()

    data object ShowDialogEvent : ReceivePatientEvent()
    data object HideDialogEvent : ReceivePatientEvent()
}