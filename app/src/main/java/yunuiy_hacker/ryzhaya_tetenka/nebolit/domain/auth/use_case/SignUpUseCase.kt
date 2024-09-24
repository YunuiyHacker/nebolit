package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

data class SignUpUseCase(
    val checkRegistrationByEmail: CheckRegistrationByEmail,
    val registrationUser: RegistrationUser
)