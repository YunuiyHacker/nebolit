package yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class User(
    val id: Int? = 0,
    val email: String? = null,
    val password: String? = null,
    @SerialName("passport_id")
    val passportId: Int? = 0,
    val passport: Passport? = null
) : Parcelable
