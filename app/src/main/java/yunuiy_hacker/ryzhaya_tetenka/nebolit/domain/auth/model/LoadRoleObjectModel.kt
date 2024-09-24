package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.enums.Role

data class LoadRoleObjectModel(val user_id: Int, val role: Role)