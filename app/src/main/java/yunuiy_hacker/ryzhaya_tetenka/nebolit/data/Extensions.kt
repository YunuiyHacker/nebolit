package yunuiy_hacker.ryzhaya_tetenka.nebolit.data

import kotlinx.datetime.LocalDate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.regex.Pattern

fun String.isEmail(): Boolean {
    return Pattern.compile(
        "[a-z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-z0-9][a-z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-z0-9][a-z0-9\\-]{0,25}" +
                ")+"
    ).matcher(this).matches()
}

fun Date.toLocalDate(): LocalDate {
    return LocalDate.fromEpochDays(
        (this.toInstant().toEpochMilli() / (1000 * 60 * 60 * 24)).toInt()
    )
}

fun Date.dateToString(): String {
    return SimpleDateFormat("dd.MM.yyyy").format(this)
}

fun Date.dateToLocalDateString(): String {
    return SimpleDateFormat("yyyy-MM-dd").format(this)
}

fun LocalDate?.toString(): String {
    return "${this?.year}-${if (this?.monthNumber?.toString()?.length == 1) "0${monthNumber}" else this?.monthNumber}-${if (this?.dayOfMonth?.toString()?.length == 1) "0${dayOfMonth}" else this?.dayOfMonth}"
}

fun String.toLocalDate(): LocalDate {
    return SimpleDateFormat("yyyy-MM-dd").parse(this)?.toLocalDate() ?: Date().toLocalDate()
}

fun String.toDateOfBackendFormat(): Date {
    return SimpleDateFormat("yyyy-MM-dd").parse(this)
}

fun String.toDateOfMobileFormat(): Date {
    return SimpleDateFormat("dd.MM.yyyy").parse(this)
}

fun LocalDate?.toPassportDate(): String {
    return "${if (this?.dayOfMonth?.toString()?.length == 1) "0${dayOfMonth}" else this?.dayOfMonth}.${if (this?.monthNumber?.toString()?.length == 1) "0${monthNumber}" else this?.monthNumber}.${this?.year}"
}

fun String.isNumeric(): Boolean {
    val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
    return this.matches(regex)
}

fun String.isDepartmentCode(): Boolean {
    var result = false
    try {
        this.forEachIndexed { index, c ->
            if (index == 3) {
                if (c == '-')
                    result = true
                else result = false
            } else {
                if (c.isDigit())
                    result = true
                else result = false
            }
        }
    } catch (e: Exception) {
        result = false
    }
    return result
}

fun Boolean.sexToString(): String {
    return if (this) "Муж" else "Жен"
}