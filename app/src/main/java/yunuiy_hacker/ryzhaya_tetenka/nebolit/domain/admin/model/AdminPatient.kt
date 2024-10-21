package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model

import java.io.Serializable

data class AdminPatient(
    val user_id: Int = 0,
    val surname: String = "",
    val name: String = "",
    val lastname: String = "",
    val email: String = "",
    val password: String = "",
    val dateOfBirth: String = "",
    val addressOfBirth: String = "",
    val series: Int = 0,
    val code: Int = 0,
    val sex: Boolean = true,
    val issueDate: String = "",
    val issueOrganization: String = "",
    val departmentCode: String = "",
    val registrationAddress: String = "",
    val liveAddress: String = "",
    val insuranceCompany: String = "",
    val policy: String = "",
    val height: Int = 0,
    val weight: Float = 0.0f
) : Serializable