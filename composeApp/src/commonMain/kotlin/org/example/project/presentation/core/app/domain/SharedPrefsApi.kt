package org.example.project.presentation.core.app.domain

interface SharedPrefsApi {

    fun saveUserNumber(username: String)

    fun getUserNumber(): String?
}
