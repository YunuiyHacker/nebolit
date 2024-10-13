package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model

import kotlinx.datetime.LocalDate


data class RegistrationPatientModel(
    val user_id: Int,
    val registraion_address: String,
    val live_address: String,
    val policy: String,
    val insurance_company: String,
    val height: Int,
    val weight: Float
)