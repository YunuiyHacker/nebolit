package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph

sealed class Route(val route: String) {
    data object OnboardingScreen : Route("onboardingScreen")
    data object SignUpScreen : Route("signUpScreen")
    data object SignInScreen : Route("signInScreen")
    data object FillPersonDataScreen : Route("fillPersonDataScreen")

    data object HomeScreen : Route("homeScreen")

    fun withIntArguments(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
