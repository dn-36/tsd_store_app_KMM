package com.project.network.cargo_network

import com.project.network.cargo_network.model.CargoResponse
import com.project.network.httpClientEngine
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

class CargoClient {

    companion object{

        private var _token: String = ""

    }
    fun init(token: String): CargoClient {

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

    //Получение груза

    suspend fun getCargo(): List<CargoResponse> {

        val response = client.get("https://delta.online/api/cargo") {

        }
        println(" ////////////////////++++++++++")
        println("Получение Груза:  ${response}")
        println(" ////////////////////++++++++++")
        return response.body<List<CargoResponse>>()
    }

}