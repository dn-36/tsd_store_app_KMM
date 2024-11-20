package com.project.network.users_network

import com.project.network.common.httpClientEngine
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
    companion object {
        private var _token: String? = null
    }

    fun init(token:String?):UsersClient{
        _token = token
        return this
    }

    private val client = HttpClient(httpClientEngine) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                coerceInputValues = true

            })
        }
        install(Logging) {
            level = LogLevel.BODY // Включить логирование для отладки
        }
        defaultRequest {
            header("Authorization", "Bearer $_token")
        }
    }

    //получение всех пользователей
    suspend fun getUsers(): List<User> {

        val response = client.get("https://delta.online/api/users-company") {
        }
        println(" ${response}")
        return response.body<List<User>>()
    }

}