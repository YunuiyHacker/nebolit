package yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case

data class SaveReadPersonDataUseCase(
    val saveUser: SaveUser,
    val readUser: ReadUser,
    val savePatient: SavePatient,
    val readPatient: ReadPatient,
    val saveDoctor: SaveDoctor,
    val readDoctor: ReadDoctor,
    val savePassport: SavePassport,
    val readPassport: ReadPassport
)