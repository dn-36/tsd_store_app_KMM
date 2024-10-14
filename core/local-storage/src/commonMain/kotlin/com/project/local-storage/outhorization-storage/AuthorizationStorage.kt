package com.project.`local-storage`.`outhorization-storage`

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

class AuthorizationStorage(private val settings: Settings) {

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