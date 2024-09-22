package yunuiy_hacker.ryzhaya_tetenka.nebolit.data.net.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int? = 0,
    val surname: String? = null,
    val name: String? = null,
    val lastname: String? = null,
    val email: String? = null,
    val password: String? = null,
    val phone: String? = null
)
