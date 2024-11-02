package yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiseaseHistory(
    val id: Int? = 0,
    @SerialName("doctor_id") val doctorId: Int? = 0,
    @SerialName("type_of_payment_id") val typeOfPaymentId: Int? = 0,
    val diagnosis: String? = "",
    @SerialName("treatment_result_id") val treatmentResultId: Int? = 0,
    @SerialName("patient_id") val patientId: Int? = 0,
    @SerialName("visit_purpose_id") val visitPurposeId: Int? = 0,
    val date: LocalDate? = null,
    @SerialName("doctors") val doctor: Doctor? = null,
    @SerialName("types_of_payment") val typeOfPayment: TypeOfPayment? = null,
    @SerialName("treatment_results") val treatmentResult: TreatmentResult? = null,
    @SerialName("patients") val patient: Patient? = null,
    @SerialName("visit_purposes") val visitPurpose: VisitPurpose? = null
)
