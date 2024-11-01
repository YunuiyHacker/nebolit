package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.mappers

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Doctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toPassportDate
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.AdminDoctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.AdminPatient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.RegistrationDoctorModel

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

fun MutableList<Doctor>.toAdminDoctors(): MutableList<AdminDoctor> {
    val mutableList: MutableList<AdminDoctor> = mutableListOf()
    this.forEach { doctor ->
        mutableList.add(
            AdminDoctor(
                id = doctor.id!!,
                user_id = doctor.user?.id!!,
                surname = doctor.user?.passport?.surname.toString(),
                name = doctor.user?.passport?.name.toString(),
                lastname = doctor.user?.passport?.lastname.toString(),
                email = doctor.user?.email.toString(),
                password = doctor.user?.password.toString(),
                dateOfBirth = doctor.user?.passport?.dateOfBirth.toPassportDate(),
                addressOfBirth = doctor.user?.passport?.addressOfBirth.toString(),
                series = doctor.user?.passport?.series ?: 0,
                code = doctor.user?.passport?.code ?: 0,
                sex = doctor.user?.passport?.sex ?: true,
                issueDate = doctor.user?.passport?.issueDate.toPassportDate(),
                issueOrganization = doctor.user?.passport?.issueOrganization.toString(),
                departmentCode = doctor.user?.passport?.departmentCode.toString(),
                specializationId = doctor.specializationId!!,
                specializationTitle = doctor.specialization?.title.toString(),
                licenzeNumber = doctor.licenzeNumber.toString()
            )
        )
    }
    return mutableList
}

fun RegistrationDoctorModel.toDataDoctor(): Doctor {
    return Doctor(
        userId = this.user_id,
        specializationId = this.specializationId,
        licenzeNumber = this.licenzeNumber
    )
}