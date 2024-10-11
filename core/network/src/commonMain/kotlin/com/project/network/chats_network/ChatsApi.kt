package com.project.network.chats_network

import com.project.network.ConstData
import com.project.network.httpClientEngine
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class ChatsApi {

    var token: String = ConstData.TOKEN

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

    // Получение списка заметок
    suspend fun getChats(): List<ChatsResponse> {


        val response = client.get("https://delta.online/api/chats") {
            parameter("active", 1)
            // для получения принятых пользователем заметок
        }

        return response.body()
    }


    // Обновление заметки
    suspend fun getListMassengers(noteId: String): String {

        return try {
            val response = client.get("https://delta.online/api/chats/dw619qfj-9xcwp78q-wp72in5v?page=1") {
                parameter("active", 1)

            }
            println("zzzzzzzzz ${response.body<String>()} ")
            response.body()
        } catch (e: Exception) {
            println("UPDATE Note: Error - ${e.message}")
            throw e // Или обработайте ошибку по-другому
        }
    }



}