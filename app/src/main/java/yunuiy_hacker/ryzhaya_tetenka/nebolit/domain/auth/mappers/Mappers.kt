package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.mappers

import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.net.model.Patient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.net.model.User
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.RegistrationPatientModel
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.model.SignUpModel
import kotlin.math.sign

fun SignUpModel.toDataUser(): User {
    return User(
        surname = this.surname,
        name = this.name,
        lastname = this.lastname,
        email = this.email,
        password = this.password
    )
}

fun RegistrationPatientModel.toDataPatient(): Patient {
    return Patient(
        user_id = this.user_id,
        date_of_birth = this.date_of_birth,
        sex = this.sex,
        registration_address = this.registraion_address,
        live_address = this.live_address,
        policy = this.policy,
        insurance_company = this.insurance_company,
        height = this.height,
        weight = this.weight
    )
}