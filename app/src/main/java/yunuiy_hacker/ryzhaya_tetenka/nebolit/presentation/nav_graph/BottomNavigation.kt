package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.Primary

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    items: List<BottomNavigationItem> = listOf(
        BottomNavigationItem(
            route = Route.HomeScreen.route,
            icon = Icons.Default.Home,
            title = "Главная"
        ),
        BottomNavigationItem(
            route = Route.ProfileScreen.route,
            icon = Icons.Default.Person,
            title = "Профиль"
        )
    )
) {
    NavigationBar(modifier = modifier) {
        items.forEach { item ->
            NavigationBarItem(
                selected = navController.currentBackStackEntry?.destination?.route == item.route,
                onClick = { navController.navigate(item.route) },
                icon = { Icon(imageVector = item.icon, contentDescription = "") },
                label = {
                    Text(
                        text = item.title,
                        color = if (navController.currentBackStackEntry?.destination?.route == item.route) Primary else MaterialTheme.colorScheme.onSurface
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Primary,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

data class BottomNavigationItem(val route: String, val icon: ImageVector, val title: String)