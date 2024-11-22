package com.project.network.units_measurement_network

import com.project.network.common.httpClientEngine
import com.project.network.units_measurement_network.model.UnitResponse
import com.project.network.valuta_network.CurrencyClient
import com.project.network.valuta_network.model.CurrencyResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class UnitsMeasurementClient {

    companion object{
        private var _token: String = ""
    }
    fun init(token: String): UnitsMeasurementClient {
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

    // получение всех единиц измерения

    suspend fun getUnits(): List<UnitResponse> {

        val response = client.get("https://delta.online/api/unit") {

        }
        println(" ////////////////////++++++++++")
        println("единицы измерения получение:  ${response}")
        println(" ////////////////////++++++++++")

        return response.body<List<UnitResponse>>()
    }

    // Создание новой удиницы измерения

    suspend fun createUnits (name: String): HttpResponse {

        val requestBody = mapOf(
            "name" to name
        )
        return try {

            val response = client.post("https://delta.online/api/unit") {

                contentType(ContentType.Application.Json)

                setBody(requestBody)
            }
            println("Создание единицы измерения: ${response.toString()}")
            response
        } catch (e: Exception) {
            println("CREATE единицы измерения: Error - ${e.message}")
            throw e
        }
    }

    // Обновление новой удиницы измерения

    suspend fun updateUnits (name: String, ui: String): HttpResponse {

        val requestBody = mapOf(
            "name" to name
        )
        return try {

            val response = client.put("https://delta.online/api/unit/${ui}") {

                contentType(ContentType.Application.Json)

                setBody(requestBody)
            }
            println("Обновление единицы измерения: ${response.toString()}")
            response
        } catch (e: Exception) {
            println("ERROR UPDATE единицы измерения: Error - ${e.message}")
            throw e
        }
    }

    // Удаление единицы измерения

    suspend fun deleteUnit ( ui: String ): HttpResponse {

        return try {

            val response = client.delete("https://delta.online/api/unit/${ui}") {

                contentType(ContentType.Application.Json) // Установка типа контента
            }
            println("Удаление единицы измерения: ${response.toString()}")
            response
        } catch (e: Exception) {
            println("ERROR DELETE единицы измерения: Error - ${e.message}")
            throw e
        }
    }

}