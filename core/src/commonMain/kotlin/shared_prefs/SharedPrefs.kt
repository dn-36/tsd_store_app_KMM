package shared_prefs

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

class SharedPreferences(private val settings: Settings) {

    companion object {
        private const val USERNAME_KEY = "username"
    }

    fun saveUsername(username: String) {
        settings[USERNAME_KEY] = username
    }

    fun getUsername(): String? {
        return settings[USERNAME_KEY]
    }
}