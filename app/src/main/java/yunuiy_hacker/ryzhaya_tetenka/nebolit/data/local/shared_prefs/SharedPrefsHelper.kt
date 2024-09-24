package yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs

import android.content.Context

class SharedPrefsHelper(context: Context) {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    //user data

    var user_id
        set(value) {
            with(prefs.edit()) {
                if (value < 0) {
                    remove(USER_ID)
                } else putInt(USER_ID, value)
                apply()
            }
        }
        get() = prefs.getInt(USER_ID, 0)

    var surname
        set(value) {
            with(prefs.edit()) {
                if (value.isNullOrEmpty()) remove(SURNAME)
                else putString(SURNAME, value)
                apply()
            }
        }
        get() = prefs.getString(SURNAME, "")

    var name
        set(value) {
            with(prefs.edit()) {
                if (value.isNullOrEmpty()) remove(NAME)
                else putString(NAME, value)
                apply()
            }
        }
        get() = prefs.getString(NAME, "")

    var lastname
        set(value) {
            with(prefs.edit()) {
                if (value.isNullOrEmpty()) remove(LASTNAME)
                else putString(LASTNAME, value)
                apply()
            }
        }
        get() = prefs.getString(LASTNAME, "")

    var email
        set(value) {
            with(prefs.edit()) {
                if (value.isNullOrEmpty()) remove(EMAIL)
                else putString(EMAIL, value)
                apply()
            }
        }
        get() = prefs.getString(EMAIL, "")

    //patient data

    var patient_id
        set(value) {
            with(prefs.edit()) {
                if (value < 0) {
                    remove(PATIENT_ID)
                } else putInt(PATIENT_ID, value)
                apply()
            }
        }
        get() = prefs.getInt(PATIENT_ID, 0)

    var date_of_birth
        set(value) {
            with(prefs.edit()) {
                if (value.isNullOrEmpty()) {
                    remove(DATE_OF_BIRTH)
                } else putString(DATE_OF_BIRTH, value)
                apply()
            }
        }
        get() = prefs.getString(DATE_OF_BIRTH, "")

    var sex
        set(value) {
            with(prefs.edit()) {
                putBoolean(SEX, value)
                apply()
            }
        }
        get() = prefs.getBoolean(SEX, true)

    var registration_address
        set(value) {
            with(prefs.edit()) {
                if (value.isNullOrEmpty()) remove(REGISTRATION_ADDRESS)
                else putString(REGISTRATION_ADDRESS, value)
                apply()
            }
        }
        get() = prefs.getString(REGISTRATION_ADDRESS, "")

    var live_address
        set(value) {
            with(prefs.edit()) {
                if (value.isNullOrEmpty()) remove(LIVE_ADDRESS)
                else putString(LIVE_ADDRESS, value)
                apply()
            }
        }
        get() = prefs.getString(LIVE_ADDRESS, "")

    var insurance_company
        set(value) {
            with(prefs.edit()) {
                if (value.isNullOrEmpty()) remove(INSURANCE_COMPANY)
                else putString(INSURANCE_COMPANY, value)
                apply()
            }
        }
        get() = prefs.getString(INSURANCE_COMPANY, "")

    var policy
        set(value) {
            with(prefs.edit()) {
                if (value.isNullOrEmpty()) remove(POLICY)
                else putString(POLICY, value)
                apply()
            }
        }
        get() = prefs.getString(POLICY, "")

    var height
        set(value) {
            with(prefs.edit()) {
                if (value == 0) remove(HEIGHT)
                else putInt(HEIGHT, value)
                apply()
            }
        }
        get() = prefs.getInt(HEIGHT, 0)

    var weight
        set(value) {
            with(prefs.edit()) {
                if (value == 0.0f) remove(WEIGHT)
                else putFloat(WEIGHT, value)
                apply()
            }
        }
        get() = prefs.getFloat(WEIGHT, 0.0f)

    //doctor data

    var doctor_id
        set(value) {
            with(prefs.edit()) {
                if (value == 0) remove(DOCTOR_ID)
                else putInt(DOCTOR_ID, value)
                apply()
            }
        }
        get() = prefs.getInt(DOCTOR_ID, 0)

    var specialization_id
        set(value) {
            with(prefs.edit()) {
                if (value == 0) remove(SPECIALIZATION_ID)
                else putInt(SPECIALIZATION_ID, value)
                apply()
            }
        }
        get() = prefs.getInt(SPECIALIZATION_ID, 0)

    var specialization_title
        set(value) {
            with(prefs.edit()) {
                if (value.isNullOrEmpty()) remove(SPECIALIZATION_TITLE)
                else putString(SPECIALIZATION_TITLE, value)
                apply()
            }
        }
        get() = prefs.getString(SPECIALIZATION_TITLE, "")

    var licenze_number
        set(value) {
            with(prefs.edit()) {
                if (value.isNullOrEmpty()) remove(LICENZE_NUMBER)
                else putString(LICENZE_NUMBER, value)
                apply()
            }
        }
        get() = prefs.getString(LICENZE_NUMBER, "")

    companion object {
        const val PREFS_NAME = "storage"

        const val USER_ID = "user_id"
        const val SURNAME = "surname"
        const val NAME = "name"
        const val LASTNAME = "lastname"
        const val EMAIL = "email"

        const val PATIENT_ID = "patient_id"
        const val DATE_OF_BIRTH = "date_of_birth"
        const val SEX = "sex"
        const val REGISTRATION_ADDRESS = "registration_address"
        const val LIVE_ADDRESS = "live_address"
        const val INSURANCE_COMPANY = "insurance_company"
        const val POLICY = "policy"
        const val HEIGHT = "height"
        const val WEIGHT = "weight"

        const val DOCTOR_ID = "doctor_id"
        const val SPECIALIZATION_ID = "specialization_id"
        const val SPECIALIZATION_TITLE = "specialization_title"
        const val LICENZE_NUMBER = "licenze_number"
    }
}