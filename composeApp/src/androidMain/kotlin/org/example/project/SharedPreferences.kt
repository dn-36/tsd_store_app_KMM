package org.example.project

import android.content.Context
import android.content.SharedPreferences
import org.example.project.presentation.feature.authorization.core.SharedPrefsApi

class SharedPreferences(private val context : Context): SharedPrefsApi {
    private val NUMBER = "NUMBER"
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    override fun saveUserNumber(number: String) {
        sharedPreferences.edit().putString(NUMBER, number).apply()
    }

    override fun getUserNumber(): String {
        return sharedPreferences.getString(NUMBER, "")?:""
    }
}
