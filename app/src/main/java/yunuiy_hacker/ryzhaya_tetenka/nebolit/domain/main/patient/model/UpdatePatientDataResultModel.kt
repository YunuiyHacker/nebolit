package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.main.patient.model

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Passport
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient

data class UpdatePatientDataResultModel(
    var passport: Passport? = null,
    var patient: Patient? = null
)