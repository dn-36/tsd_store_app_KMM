package com.project.`local-storage`.`outhorization-storage`

import com.russhwolf.settings.Settings
import com.russhwolf.settings.NSUserDefaultsSettings
import platform.Foundation.NSUserDefaults

actual class CreateContextPlatform{
    actual fun get():Settings {
        val userDefaults = NSUserDefaults.standardUserDefaults()
        return NSUserDefaultsSettings(userDefaults)
    }

}