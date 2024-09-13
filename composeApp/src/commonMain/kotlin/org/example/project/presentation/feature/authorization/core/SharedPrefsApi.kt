package org.example.project.presentation.feature.authorization.core

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

interface SharedPrefsApi {

    fun saveUserNumber(username: String)

    fun getUserNumber(): String?
}
