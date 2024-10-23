package com.project.network.contragent_network

import com.project.network.ConstData
import com.project.network.contragent_network.model.ContragentResponse
import com.project.network.httpClientEngine
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ContragentClient {

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
                header("Authorization", "Bearer ${ConstData.TOKEN}")
            }
        }

         // получение всех контрагентов
         suspend fun getContragents(): List<ContragentResponse> {
             return try {
                 val response = client.get("https://delta.online/api/contragent") {
                     // здесь можно добавить заголовки или другие параметры запроса
                 }
                 println(" ////////////////////++++++++++")
                 println("Контрагенты ${response}")
                 println(" ////////////////////++++++++++")

                 // Десериализуем ответ в список объектов LocationResponse
                 val responseBody = response.bodyAsText()
                 Json { ignoreUnknownKeys = true }.decodeFromString<List<ContragentResponse>>(responseBody)
             } catch (e: Exception) {
                 println("Ошибка при получении контрагентов: ${e.message}")
                 // Возвращаем пустой список в случае ошибки
                 emptyList()
             }
         }

}