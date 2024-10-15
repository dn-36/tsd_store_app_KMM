package org.example.project.app.domain

interface LocalStorageAppApi {
    suspend fun number():String
    suspend fun saveNumber(number:String)
}