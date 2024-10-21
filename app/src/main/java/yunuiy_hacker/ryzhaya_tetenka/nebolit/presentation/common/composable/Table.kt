package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.Primary
import yunuiy_hacker.ryzhaya_tetenka.nebolit.ui.theme.Primary40

@Composable
fun <T> Table(
    columnCount: Int,
    cellWidth: (index: Int) -> Dp,
    data: MutableList<T>,
    modifier: Modifier = Modifier,
    color: Color,
    headerCellContent: @Composable (index: Int) -> Unit,
    cellContent: @Composable (index: Int, item: T) -> Unit,
    selectedIndex: Int,
    onChangeSelectedIndex: (Int) -> Unit
) {
    Surface(
        modifier = modifier,
        color = color
    ) {
        LazyRow(
            modifier = Modifier
                .padding(2.dp)
                .clip(shape = RoundedCornerShape(20.dp))
        ) {
            items((0 until columnCount).toList()) { columnIndex ->
                Column {
                    (0..data.size).forEach { index ->
                        Surface(
                            border = BorderStroke(1.dp, Primary),
                            modifier = Modifier
                                .width(cellWidth(columnIndex))
                                .clickable {
                                    if (index > 0)
                                        onChangeSelectedIndex(index - 1)
                                },
                            color = if (selectedIndex == index - 1) Primary40 else MaterialTheme.colorScheme.secondaryContainer
                        ) {
                            if (index == 0) {
                                headerCellContent(columnIndex)
                            } else {
                                cellContent(columnIndex, data[index - 1])
                            }
                        }
                    }
                }
            }
        }
    }
}