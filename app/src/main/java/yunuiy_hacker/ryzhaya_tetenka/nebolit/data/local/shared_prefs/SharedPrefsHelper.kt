package yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs

import android.content.Context

class SharedPrefsHelper(context: Context) {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    //passport data

    var passport_id
        set(value) {
            with(prefs.edit()) {
                if (value < 0) {
                    remove(PASSPORT_ID)
                } else putInt(PASSPORT_ID, value)
                apply()
            }
        }
        get() = prefs.getInt(PASSPORT_ID, 0)

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

    var dateOfBirth
        set(value) {
            with(prefs.edit()) {
                if (value.isNullOrEmpty()) {
                    remove(DATE_OF_BIRTH)
                } else putString(DATE_OF_BIRTH, value)
                apply()
            }
        }
        get() = prefs.getString(DATE_OF_BIRTH, "")

    var addressOfBirth
        set(value) {
            with(prefs.edit()) {
                if (value.isNullOrEmpty()) remove(ADDRESS_OF_BIRTH)
                else putString(ADDRESS_OF_BIRTH, value)
                apply()
            }
        }
        get() = prefs.getString(ADDRESS_OF_BIRTH, "")

    var series
        set(value) {
            with(prefs.edit()) {
                if (value < 0) remove(SERIES)
                else putInt(SERIES, value)
                apply()
            }
        }
        get() = prefs.getInt(SERIES, 0)

    var code
        set(value) {
            with(prefs.edit()) {
                if (value < 0) remove(CODE)
                else putInt(CODE, value)
                apply()
            }
        }
        get() = prefs.getInt(CODE, 0)

    var sex
        set(value) {
            with(prefs.edit()) {
                if (value == null) {
                    remove(SEX)
                } else {
                    putBoolean(SEX, value)
                }
                apply()
            }
        }
        get() = prefs.getBoolean(SEX, true)

    var issueDate
        set(value) {
            with(prefs.edit()) {
                if (value.isNullOrEmpty()) {
                    remove(ISSUE_DATE)
                } else putString(ISSUE_DATE, value)
                apply()
            }
        }
        get() = prefs.getString(ISSUE_DATE, "")

    var issueOrganization
        set(value) {
            with(prefs.edit()) {
                if (value.isNullOrEmpty()) remove(ISSUE_ORGANIZATION)
                else putString(ISSUE_ORGANIZATION, value)
                apply()
            }
        }
        get() = prefs.getString(ISSUE_ORGANIZATION, "")

    var departmentCode
        set(value) {
            with(prefs.edit()) {
                if (value.isNullOrEmpty()) remove(DEPARTMENT_CODE)
                else putString(DEPARTMENT_CODE, value)
                apply()
            }
        }
        get() = prefs.getString(DEPARTMENT_CODE, "")

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
        private const val PREFS_NAME = "storage"

        private const val PASSPORT_ID = "passport_id"
        private const val SURNAME = "surname"
        private const val NAME = "name"
        private const val LASTNAME = "lastname"
        private const val DATE_OF_BIRTH = "date_of_birth"
        private const val ADDRESS_OF_BIRTH = "address_of_birth"
        private const val SERIES = "series"
        private const val CODE = "code"
        private const val SEX = "sex"
        private const val ISSUE_DATE = "issue_date"
        private const val ISSUE_ORGANIZATION = "issue_organization"
        private const val DEPARTMENT_CODE = "department_code"

        private const val USER_ID = "user_id"
        private const val EMAIL = "email"

        private const val PATIENT_ID = "patient_id"
        private const val REGISTRATION_ADDRESS = "registration_address"
        private const val LIVE_ADDRESS = "live_address"
        private const val INSURANCE_COMPANY = "insurance_company"
        private const val POLICY = "policy"
        private const val HEIGHT = "height"
        private const val WEIGHT = "weight"

        private const val DOCTOR_ID = "doctor_id"
        private const val SPECIALIZATION_ID = "specialization_id"
        private const val SPECIALIZATION_TITLE = "specialization_title"
        private const val LICENZE_NUMBER = "licenze_number"
    }
}