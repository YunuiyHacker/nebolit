package yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model

import kotlinx.serialization.Serializable
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.parent.RoleObject

@Serializable
data class Doctor(
    val id: Int? = 0,
    val user_id: Int? = 0,
    val specialization_id: Int? = 0,
    val licenze_number: String? = "",
    val user: User? = null,
    val specialization: Specialization? = null
) : RoleObject()