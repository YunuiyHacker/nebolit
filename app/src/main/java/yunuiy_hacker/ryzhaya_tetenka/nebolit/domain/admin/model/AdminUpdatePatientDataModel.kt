package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model

data class AdminUpdatePatientDataModel(
    val registrationAddress: String,
    val liveAddress: String,
    val insuranceCompany: String,
    val policy: String,
    val height: Int,
    val weight: Float,
    val email: String,
    val surname: String,
    val name: String,
    val lastname: String,
    val dateOfBirth: String,
    val addressOfBirth: String,
    val series: Int,
    val code: Int,
    val sex: Boolean,
    val issueDate: String,
    val issueOrganization: String,
    val departmentCode: String
)
