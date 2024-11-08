package com.project.network.specifications_network

import com.project.network.httpClientEngine
import com.project.network.specifications_network.model.SpecificResponse
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

class SpecificationsClient {

    companion object{
        private var _token: String = ""
    }
    fun init(token: String): SpecificationsClient {
        _token = token
        return this
    }

    private val client = HttpClient(httpClientEngine) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // Игнорировать неизвестные поля
                isLenient = true// Быть гибким с форматом JSON
                explicitNulls = false
            })
        }
        install(Logging) {
            level = LogLevel.BODY // Включить логирование для отладки
        }
        defaultRequest {
            header("Authorization", "Bearer ${_token}")
        }
    }

    //Получение спецификаций

    suspend fun getSpecifications(): List<SpecificResponse> {

        val response = client.get("https://delta.online/api/specification") {

        }
        println(" ////////////////////++++++++++")
        println("Получение Спецификаций:  ${response}")
        println(" ////////////////////++++++++++")
        return response.body<List<SpecificResponse>>()
    }

}