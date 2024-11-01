package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.disease_history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun DiseaseHistoryScreen(
    navController: NavHostController, viewModel: DiseaseHistoryViewModel = hiltViewModel()
) {
    val isDarkTheme = viewModel.dataStoreHelper.getTheme().collectAsState(initial = false).value

    Scaffold(containerColor = MaterialTheme.colorScheme.onBackground, topBar = {
        TopAppBar(title = {
            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = -24.dp),
                    text = "История болезней",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        }, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = if (isDarkTheme) Color(
                0xFF131313
            ) else Color(0xFFF9F9F9)
        )
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}