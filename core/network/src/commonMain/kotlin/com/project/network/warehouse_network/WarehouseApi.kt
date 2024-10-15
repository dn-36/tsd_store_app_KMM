package com.project.network.warehouse_network

import com.project.network.httpClientEngine
import com.project.network.notes_network.model.NoteResponse
import com.project.network.warehouse_network.model.Warehouse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


object WarehouseApi {

    var token: String = ""

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
            header("Authorization", "Bearer $token")
        }
    }

    // Получение списка складов
    suspend fun getWarehouse(): List<Warehouse> {
        val response = client.get("https://delta.online/api/local-store")
        println("Получение списка складов: ${response}")
        return response.body<List<Warehouse>>()
    }

    // Создание нового склада
    suspend fun createWarehouse(name: String, localId: String): HttpResponse {

        val requestBody = mapOf(
            "name" to name,
            "local_id" to localId
        )
        return try {
            val response = client.post("https://delta.online/api/local-store") {
                contentType(ContentType.Application.Json)
                setBody(requestBody)
            }
            println("Создание склада: ${response.toString()}")
            response
        } catch (e: Exception) {
            println("CREATE LocalStore: Error - ${e.message}")
            throw e
        }
    }

    // Обновление склада
    suspend fun updateWarehouse(ui: String, name: String, localId: String): HttpResponse {
        val requestBody = mapOf(
            "name" to name,
            "local_id" to localId
        )
        return try {
            val response = client.put("https://delta.online/api/local-store/${ui}") {
                contentType(ContentType.Application.Json)
                setBody(requestBody)
            }
            println("Обновление склада: ${response.toString()}")
            response
        } catch (e: Exception) {
            println("UPDATE LocalStore: Error - ${e.message}")
            throw e
        }
    }

    // Удаление склада
    suspend fun deleteWarehouse(ui: String): HttpResponse {
        return try {
            val response = client.delete("https://delta.online/api/local-store/${ui}") {
                contentType(ContentType.Application.Json) // Установка типа контента
            }
            println("Удаление склада: ${response.toString()}")
            response
        } catch (e: Exception) {
            println("DELETE LocalStore: Error - ${e.message}")
            throw e
        }
    }

}