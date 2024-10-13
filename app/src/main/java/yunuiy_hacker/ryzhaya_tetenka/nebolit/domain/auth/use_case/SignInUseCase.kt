package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

data class SignInUseCase(
    val logIn: LogIn,
    val defineRole: DefineRole,
    val loadRoleObject: LoadRoleObject,
    val getPassportById: GetPassportById,
    val getSpecializationById: GetSpecializationById
)