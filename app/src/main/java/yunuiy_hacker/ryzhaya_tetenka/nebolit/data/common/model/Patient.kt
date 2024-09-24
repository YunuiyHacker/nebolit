package yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.parent.RoleObject

@Serializable
data class Patient(
    val id: Int? = 0,
    val user_id: Int? = 0,
    val date_of_birth: LocalDate? = null,
    val sex: Boolean? = null,
    val registration_address: String? = null,
    val live_address: String? = null,
    val insurance_company: String? = null,
    val policy: String? = null,
    val height: Int? = null,
    val weight: Float? = null,
    val user: User? = null
) : RoleObject()