package yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class User(
    val id: Int? = 0,
    val surname: String? = null,
    val name: String? = null,
    val lastname: String? = null,
    val email: String? = null,
    val password: String? = null
) : Parcelable
