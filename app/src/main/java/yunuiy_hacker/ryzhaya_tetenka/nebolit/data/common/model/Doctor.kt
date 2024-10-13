package yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.parent.RoleObject

@Serializable
data class Doctor(
    val id: Int? = 0,
    @SerialName("user_id")
    val userId: Int? = 0,
    @SerialName("specialization_id")
    val specializationId: Int? = 0,
    @SerialName("licenze_number")
    val licenzeNumber: String? = "",
    val user: User? = null,
    var specialization: Specialization? = null
) : RoleObject()