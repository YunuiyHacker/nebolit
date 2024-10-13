package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model

import kotlinx.datetime.LocalDate

data class RecordPassportModel(
    val surname: String,
    val name: String,
    val lastname: String,
    val dateOfBirth: LocalDate,
    val addressOfBirth: String,
    val series: String,
    val code: String,
    val sex: Boolean,
    val issueDate: LocalDate,
    val issueOrganization: String,
    val departamentCode: String
)
