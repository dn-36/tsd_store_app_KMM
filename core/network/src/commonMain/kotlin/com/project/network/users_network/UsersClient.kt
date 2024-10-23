package com.project.network.users_network

import com.project.network.ConstData
import com.project.network.httpClientEngine
import com.project.network.users_network.model.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class UsersClient {

    private val client = HttpClient(httpClientEngine) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // Игнорировать неизвестные поля
                isLenient = true // Быть гибким с форматом JSON
                coerceInputValues = true // Принудительно преобразовывает несовпадающие типы, например, null к правильному типу

            })
        }
        install(Logging) {
            level = LogLevel.BODY // Включить логирование для отладки
        }
        defaultRequest {
            header("Authorization", "Bearer ${ConstData.TOKEN}")
        }
    }

    //получение всех пользователей
    suspend fun getUsers(): List<User> {

        val response = client.get("https://delta.online/api/users-company") {

        }
        println(" ////////////////////++++++++++")
        println(" ${response}")
        println(" ////////////////////++++++++++")
        return response.body<List<User>>()
    }

}