package org.example.project.presentation.core.app.datasource.shared_prefs

import org.example.project.presentation.core.app.domain.SharedPrefsApi
import shared_prefs.SharedPreferences


class SharedPrefsImpl(private val sharedPrefs: SharedPreferences): SharedPrefsApi {
    override fun saveUserNumber(username: String) {
        sharedPrefs.saveUsername(username)
    }

    override fun getUserNumber(): String {
       return sharedPrefs.getUsername()?:""
    }


}