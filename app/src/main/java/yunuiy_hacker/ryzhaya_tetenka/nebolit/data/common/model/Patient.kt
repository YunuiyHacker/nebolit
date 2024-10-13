package yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.parent.RoleObject

@Serializable
data class Patient(
    val id: Int? = 0,
    @SerialName("user_id")
    val userId: Int? = 0,
    @SerialName("registration_address")
    val registrationAddress: String? = null,
    @SerialName("live_address")
    val liveAddress: String? = null,
    @SerialName("insurance_company")
    val insuranceCompany: String? = null,
    val policy: String? = null,
    val height: Int? = null,
    val weight: Float? = null,
    val user: User? = null
) : RoleObject()