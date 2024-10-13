package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph.BottomNavigation

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    Scaffold(bottomBar = {
        BottomNavigation(navController = navController)
    }) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {

        }
    }
}