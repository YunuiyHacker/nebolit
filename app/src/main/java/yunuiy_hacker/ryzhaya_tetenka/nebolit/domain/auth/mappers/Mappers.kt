package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.mappers

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Passport
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.RecordPassportModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.RegistrationPatientModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.SignUpModel

fun RecordPassportModel.toDataPassport(): Passport {
    return Passport(
        surname = this.surname,
        name = this.name,
        lastname = this.lastname,
        dateOfBirth = this.dateOfBirth,
        addressOfBirth = this.addressOfBirth,
        series = this.series.toInt(),
        code = this.code.toInt(),
        sex = this.sex,
        issueDate = this.issueDate,
        issueOrganization = this.issueOrganization,
        departmentCode = this.departamentCode
    )
}

fun SignUpModel.toDataUser(): User {
    return User(
        email = this.email,
        password = this.password
    )
}

fun RegistrationPatientModel.toDataPatient(): Patient {
    return Patient(
        userId = this.user_id,
        registrationAddress = this.registraion_address,
        liveAddress = this.live_address,
        policy = this.policy,
        insuranceCompany = this.insurance_company,
        height = this.height,
        weight = this.weight
    )
}