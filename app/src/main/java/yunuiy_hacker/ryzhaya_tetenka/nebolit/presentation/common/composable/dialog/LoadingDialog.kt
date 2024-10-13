package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.BUTTON_CORNER_RADIUS

@Composable
fun LoadingDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(BUTTON_CORNER_RADIUS))
                .background(
                    color = if (isSystemInDarkTheme()) Color(0xFF131313) else Color(
                        0xFFFFFFFF
                    )
                )
        ) {
            CircularProgressIndicator(modifier = Modifier.padding(20.dp))
        }
    }
}