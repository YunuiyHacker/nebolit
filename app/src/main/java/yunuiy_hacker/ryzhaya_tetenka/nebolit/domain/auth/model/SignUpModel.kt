package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model

import java.sql.Timestamp

data class SignUpModel(
    val surname: String,
    val name: String,
    val lastname: String,
    val email: String,
    val password: String
)
