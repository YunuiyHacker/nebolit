package yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model

import android.os.Parcelable
import kotlinx.datetime.LocalDate
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Passport(
    val id: Int? = 0,
    val surname: String? = null,
    val name: String? = null,
    val lastname: String? = null,
    @SerialName("date_of_birth")
    val dateOfBirth: @RawValue LocalDate? = null,
    @SerialName("address_of_birth")
    val addressOfBirth: String? = null,
    val series: Int? = null,
    val code: Int? = null,
    val sex: Boolean? = null,
    @SerialName("issue_date")
    val issueDate: @RawValue LocalDate? = null,
    @SerialName("issue_organization")
    val issueOrganization: String? = null,
    @SerialName("department_code")
    val departmentCode: String? = null
) : Parcelable
