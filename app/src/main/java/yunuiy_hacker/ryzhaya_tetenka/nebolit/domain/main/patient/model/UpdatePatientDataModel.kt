package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.patient.model

import java.util.Date

data class UpdatePatientDataModel(
    val surname: String,
    val name: String,
    val lastname: String,
    val dateOfBirth: Date,
    val registrationAddress: String,
    val liveAddress: String,
    val policy: String,
    val insuranceCompany: String,
    val height: Int,
    val weight: Float
)