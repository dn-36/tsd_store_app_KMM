package com.project.network.characterisrics_products_network

import com.project.network.characterisrics_products_network.model.Characteristic
import com.project.network.common.httpClientEngine
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class CharacteristicsClient {

    companion object{
        private var _token: String = ""
    }
    fun init(token: String): CharacteristicsClient {
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
            header("Authorization", "Bearer $_token")
        }
    }

    // получение всех характеристик

    suspend fun getCharacteristics(): List<Characteristic> {

        val response = client.get("https://delta.online/api/parametrs-products-admin") {

        }
        println(" ////////////////////++++++++++")
        println("получение характеристик:  ${response}")
        println(" ////////////////////++++++++++")

        return response.body<List<Characteristic>>()
    }

}