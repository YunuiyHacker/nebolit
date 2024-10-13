package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SettingsComposable(
    modifier: Modifier = Modifier,
    darkThemeChecked: Boolean,
    onDarkThemeOnChange: (Boolean) -> Unit,
    onExitRowClick: () -> Unit
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = if (darkThemeChecked) Icons.Default.DarkMode else Icons.Default.LightMode,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = "Темная тема",
                color = MaterialTheme.colorScheme.onSurface
            )
            Switch(checked = darkThemeChecked, onCheckedChange = {
                onDarkThemeOnChange(it)
            })
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .clickable(interactionSource = interactionSource, indication = null) {
                    onExitRowClick()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "", tint = Color.Red)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = "Выйти из аккаунта",
                color = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

    }
}