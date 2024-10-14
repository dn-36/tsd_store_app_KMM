package com.project.network.warehouse_network

import com.project.network.httpClientEngine
import com.project.network.notes_network.NotesApi
import com.project.network.notes_network.model.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object WarehouseApi {

    var token: String = ""

    private val client = HttpClient(httpClientEngine) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // Игнорировать неизвестные поля
                isLenient = true // Быть гибким с форматом JSON
            })
        }
        install(Logging) {
            level = LogLevel.BODY // Включить логирование для отладки
        }
        defaultRequest {
            header("Authorization", "Bearer $token")
        }
    }

    // получение всех локаций
    suspend fun getLocations(): String {
        return try {
            val response = client.get("https://delta.online/api/contragent") {
                // здесь можно добавить заголовки или другие параметры запроса
            }
            println(" ////////////////////++++++++++")
            println("лоКАЦИЯ ${response}")
            println(" ////////////////////++++++++++")
            response.bodyAsText()
        } catch (e: Exception) {
            // Ловим любые исключения, которые могут возникнуть
            println("Ошибка при получении локаций: ${e.message}")
            // Возвращаем ошибочное сообщение или пустую строку, в зависимости от требований
            ""
        }
    }

}