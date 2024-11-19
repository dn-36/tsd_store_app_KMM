package com.project.network.valuta_network

import com.project.network.httpClientEngine
import com.project.network.valuta_network.model.CurrencyResponse
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

class CurrencyClient {

    companion object{
        private var _token: String = ""
    }
    fun init(token: String): CurrencyClient {
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

    // получение всех валют

    suspend fun getCurrency(): List<CurrencyResponse> {

        val response = client.get("https://delta.online/api/valuta") {

        }
        println(" ////////////////////++++++++++")
        println("валюты:  ${response}")
        println(" ////////////////////++++++++++")
        return response.body<List<CurrencyResponse>>()
    }

}