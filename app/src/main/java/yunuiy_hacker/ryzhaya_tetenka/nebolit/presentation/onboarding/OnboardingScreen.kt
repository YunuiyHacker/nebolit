package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import yunuiy_hacker.ryzhaya_tetenka.nebolit.R
import yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.BUTTON_CORNER_RADIUS
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.Primary

@Composable
fun OnboardingScreen(
    navController: NavHostController
) {
    Surface(color = MaterialTheme.colorScheme.onBackground) {
        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .scale(1.5f),
                painter = painterResource(id = R.drawable.onboarding_img1),
                contentDescription = "",
                contentScale = ContentScale.Fit
            )
            Text(
                modifier = Modifier.weight(0.2f),
                text = buildAnnotatedString {
                    append("Добро пожаловать\n в ")
                    withStyle(style = SpanStyle(color = Primary)) {
                        append("Неболит")
                    }
                    append("!")
                },
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                lineHeight = 36.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp
                    ), onClick = {
                    navController.navigate(Route.SignInScreen.route) {
                        popUpTo(0)
                    }
                }, shape = RoundedCornerShape(BUTTON_CORNER_RADIUS)
            ) {
                Text(text = "Продолжить", color = Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}