package yunuiy_hacker.ryzhaya_tetenka.nebolit.data

import kotlinx.datetime.LocalDate
import java.text.SimpleDateFormat
import java.util.Date


fun Date.toLocalDate(): LocalDate {
    return LocalDate.fromEpochDays(
        (this.toInstant().toEpochMilli() / (1000 * 60 * 60 * 24)).toInt()
    )
}

fun LocalDate?.toString(): String {
    return "${this?.year}-${if (this?.monthNumber?.toString()?.length == 1) "0${monthNumber}" else this?.monthNumber}-${if (this?.dayOfMonth?.toString()?.length == 1) "0${dayOfMonth}" else this?.dayOfMonth}"
}

fun String.toLocalDate(): LocalDate {
    return SimpleDateFormat("yyyy-MM-dd").parse(this)?.toLocalDate() ?: Date().toLocalDate()
}

fun String.isNumeric(): Boolean {
    val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
    return this.matches(regex)
}