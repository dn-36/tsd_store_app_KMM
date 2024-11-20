package com.project.network.contragent_network

import com.project.network.common.ConstData
import com.project.network.contragent_network.model.ContragentResponse
import com.project.network.common.httpClientEngine
import io.ktor.client.HttpClient
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
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
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

    // Удаление контрагента
    suspend fun deleteContragent(id: Int): HttpResponse {
        return try {
            val response = client.delete("https://delta.online/api/contragent/${id}") {
                contentType(ContentType.Application.Json) // Установка типа контента
            }
            println("Удаление контрагента: ${response.toString()}")
            response
        } catch (e: Exception) {
            println("DELETE CONTRAGENT: Error - ${e.message}")
            throw e
        }
    }

    // Создание нового контрагента
    suspend fun createContragent (name: String): HttpResponse {

        val requestBody = mapOf(
            "name" to name
        )
        return try {

            val response = client.post("https://delta.online/api/contragent") {

                contentType(ContentType.Application.Json)

                setBody(requestBody)
            }
            println("Создание контрагента: ${response.toString()}")
            response
        } catch (e: Exception) {
            println("CREATE CONTRAGENT: Error - ${e.message}")
            throw e
        }
    }

    // Обновление склада
    suspend fun updateContragent( id: Int, name: String): HttpResponse {

        val requestBody = mapOf(

            "name" to name
        )

        return try {

            val response = client.put("https://delta.online/api/contragent/${id}") {

                contentType(ContentType.Application.Json)

                setBody(requestBody)
            }

            println("Обновление контрагента: ${response.toString()}")

            response

        } catch (e: Exception) {

            println("UPDATE CONTRAGENT: Error - ${e.message}")

            throw e
        }
    }

}