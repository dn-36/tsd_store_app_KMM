package com.project.network.category_network

import com.project.network.category_network.model.CategoryResponse
import com.project.network.httpClientEngine
import com.project.network.locations_network.model.CreateLocationRequest
import com.project.network.services_network.ServicesClient
import com.project.network.services_network.model.ServiceResponse
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

class CategoryClient {

    companion object{

        private var _token: String = ""

    }
    fun init(token: String): CategoryClient {

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

    //Получение категорий

    suspend fun getCategories(): List<CategoryResponse> {

        val response = client.get("https://delta.online/api/category") {

        }
        println(" ////////////////////++++++++++")
        println("Получение категорий:  ${response}")
        println(" ////////////////////++++++++++")
        return response.body<List<CategoryResponse>>()
    }

    // Создание категории
    suspend fun createCategory (

        name: String

    ): HttpResponse {

        val requestBody = mapOf(

            "name" to name

        )
        return try {

            val response = client.post("https://delta.online/api/category") {

                contentType(ContentType.Application.Json)

                setBody(requestBody)
            }
            println("Создание категории: ${response}")

            response

        } catch (e: Exception) {

            println("ошибка создания категории: Error - ${e.message}")

            throw e
        }
    }

    // Обновление категории
    suspend fun updateCategory (

        name: String,

        id: Int

    ): HttpResponse {

        val requestBody = mapOf(

            "name" to name

        )
        return try {

            val response = client.put("https://delta.online/api/category/${id}") {

                contentType(ContentType.Application.Json)

                setBody(requestBody)
            }
            println("Обновление категории: ${response}")

            response

        } catch (e: Exception) {

            println("ошибка обновления категории: Error - ${e.message}")

            throw e
        }
    }

    // Удаление категории
    suspend fun deleteCategory (

        id: Int

    ): HttpResponse {

        return try {

            val response = client.delete("https://delta.online/api/category/${id}") {

                contentType(ContentType.Application.Json)

            }
            println("Удаление категории: ${response}")

            response

        } catch (e: Exception) {

            println("ошибка удаления категории: Error - ${e.message}")

            throw e
        }
    }

}