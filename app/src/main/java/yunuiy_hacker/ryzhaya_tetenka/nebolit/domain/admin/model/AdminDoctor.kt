package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model

data class AdminDoctor(
    val id: Int = 0,
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
    val specializationId: Int = 0,
    val specializationTitle: String = "",
    val licenzeNumber: String = "",
)