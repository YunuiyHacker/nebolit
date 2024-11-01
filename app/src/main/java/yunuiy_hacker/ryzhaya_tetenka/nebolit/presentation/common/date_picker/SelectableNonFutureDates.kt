package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.common.date_picker

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
object SelectableNonFutureDates : SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        return utcTimeMillis <= System.currentTimeMillis()
    }

    override fun isSelectableYear(year: Int): Boolean {
        return year <= LocalDate.now().year
    }
}

@OptIn(ExperimentalMaterial3Api::class)
object SelectableNonPastAndFuture24dDates : SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        return (utcTimeMillis >= System.currentTimeMillis() && utcTimeMillis <= System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 13))
    }

    override fun isSelectableYear(year: Int): Boolean {
        return year >= LocalDate.now().year
    }
}