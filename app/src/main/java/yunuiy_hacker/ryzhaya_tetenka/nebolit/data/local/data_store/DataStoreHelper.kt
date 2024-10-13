package yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store

import android.content.Context
import android.provider.Settings.Global
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import io.ktor.util.valuesOf
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DataStoreHelper(private val context: Context) {

    val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(
        name = USER_SETTINGS
    )

    fun getTheme(): Flow<Boolean> {
        return context.dataStore.data.map { value: Preferences ->
            value[PreferencesKeys.DARK_THEME_KEY] ?: false
        }
    }

    suspend fun setTheme(value: Boolean) {
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferencesKeys.DARK_THEME_KEY] = value
        }
    }

    fun getAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { value: Preferences ->
            value[PreferencesKeys.APP_ENTRY_KEY] ?: false
        }
    }

    suspend fun setAppEntry() {
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferencesKeys.APP_ENTRY_KEY] = true
        }
    }

    private object PreferencesKeys {
        val DARK_THEME_KEY = booleanPreferencesKey(name = DARK_THEME)
        val APP_ENTRY_KEY = booleanPreferencesKey(name = APP_ENTRY)
    }

    companion object {
        private const val USER_SETTINGS = "user_settings"

        private const val DARK_THEME = "dark_theme"
        private const val APP_ENTRY = "app_entry"
    }
}