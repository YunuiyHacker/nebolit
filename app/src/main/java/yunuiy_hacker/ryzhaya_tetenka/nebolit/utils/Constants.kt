package yunuiy_hacker.ryzhaya_tetenka.nebolit.utils

import java.util.regex.Pattern

object Constants {
    val PATTERN_EMAIL =
        Pattern.compile(
            "[a-z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-z0-9][a-z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-z0-9][a-z0-9\\-]{0,25}" +
                    ")+"
        )

    val URL = "https://hubxllvukplhaqzkivtd.supabase.co"
    val API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imh1YnhsbHZ1a3BsaGFxemtpdnRkIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjAxNzIyMzIsImV4cCI6MjAzNTc0ODIzMn0.lLwpAqntaF-vJrAxoU9i4gPetM_jY3rtlTJLrUKlauI"
}