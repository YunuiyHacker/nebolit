package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.mappers

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toPassportDate
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.AdminPatient

fun MutableList<Patient>.toAdminPatients(): MutableList<AdminPatient> {
    val mutableList: MutableList<AdminPatient> = mutableListOf()
    this.forEach { patient ->
        mutableList.add(
            AdminPatient(
                user_id = patient.user?.id!!,
                surname = patient.user?.passport?.surname.toString(),
                name = patient.user?.passport?.name.toString(),
                lastname = patient.user?.passport?.lastname.toString(),
                email = patient.user?.email.toString(),
                password = patient.user?.password.toString(),
                dateOfBirth = patient.user?.passport?.dateOfBirth.toPassportDate(),
                addressOfBirth = patient.user?.passport?.addressOfBirth.toString(),
                series = patient.user?.passport?.series ?: 0,
                code = patient.user?.passport?.code ?: 0,
                sex = patient.user?.passport?.sex ?: true,
                issueDate = patient.user?.passport?.issueDate.toPassportDate(),
                issueOrganization = patient.user?.passport?.issueOrganization.toString(),
                departmentCode = patient.user?.passport?.departmentCode.toString(),
                registrationAddress = patient.registrationAddress.toString(),
                liveAddress = patient.liveAddress.toString(),
                insuranceCompany = patient.insuranceCompany.toString(),
                policy = patient.policy.toString(),
                height = patient.height ?: 0,
                weight = patient.weight ?: 0.0f
            )
        )
    }
    return mutableList
}