package shared_prefs

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

actual class CreateContextPlatform(private val context:Context) {
    actual fun get():Settings {
            val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            return SharedPreferencesSettings(sharedPreferences)
        }
    }
