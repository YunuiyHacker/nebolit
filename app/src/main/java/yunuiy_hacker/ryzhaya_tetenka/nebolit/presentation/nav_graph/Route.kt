package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph

import com.google.gson.Gson
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DoctorSchedule
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.AdminDoctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.admin.model.AdminPatient

sealed class Route(val route: String) {
    data object OnboardingScreen : Route("onboardingScreen")
    data object SignUpScreen : Route("signUpScreen")
    data object SignInScreen : Route("signInScreen")
    data object FillPersonDataScreen : Route("fillPersonDataScreen")

    data object HomeScreen : Route("homeScreen")
    data object ProfileScreen : Route("profileScreen")

    data object EditPersonDataScreen : Route("editPersonDataScreen")

    //admin
    data object AdminMainScreen : Route("adminMainScreen")
    data object AdminPatientsListScreen : Route("adminPatientsListScreen")
    data object AdminPatientsEditScreen : Route("adminPatientsEditScreen")
    data object AdminPatientsAddScreen : Route("adminPatientsAddScreen")
    data object AdminDoctorsListScreen : Route("adminDoctorsListScreen")
    data object AdminDoctorsEditScreen : Route("adminDoctorsEditScreen")
    data object AdminDoctorsAddScreen : Route("adminDoctorsAddScreen")
    data object AdminDoctorsSchedulesScreen : Route("adminDoctorsSchedulesScreen")

    //patient
    data object SelectSpecializationScreen : Route("selectSpecializationScreen")
    data object SelectDoctorScreen : Route("selectDoctorScreen")
    data object SelectTimeScreen : Route("selectTimeScreen")
    data object DiseasesHistoryScreen : Route("diseasesHistoryScreen")

    //doctor
    data object ReceivePatientScreen : Route("receivePatientScreen")
    data object DoctorDiseaseHistoryScreen : Route("doctorDiseaseScreen")

    fun withIntArguments(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    fun withAdminPatientArgument(adminPatient: AdminPatient): String {
        val gson: Gson = Gson()

        return buildString {
            append(route)
            append("/${gson.toJson(adminPatient)}")
        }
    }

    fun withAdminDoctorArgument(adminDoctor: AdminDoctor): String {
        val gson: Gson = Gson()

        return buildString {
            append(route)
            append("/${gson.toJson(adminDoctor)}")
        }
    }

    fun withDoctorScheduleArgument(doctorSchedule: DoctorSchedule): String {
        val gson: Gson = Gson()

        return buildString {
            append(route)
            append("/${gson.toJson(doctorSchedule)}")
        }
    }
}
