package com.project.network.tape_network

import com.project.network.common.httpClientEngine
import com.project.network.tape_network.model.TapeResponse
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

class TapeClient {

    companion object{
        private var _token: String = ""
    }
    fun init(token: String): TapeClient {
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

    // получение видео

    suspend fun getVideo(): List<TapeResponse> {

        val response = client.get("https://delta.online/api/lenta") {

        }
        println(" ////////////////////++++++++++")
        println("получение видео:  ${response}")
        println(" ////////////////////++++++++++")
        return response.body<List<TapeResponse>>()
    }

    // Создание нового элемента ленты
    suspend fun createPhotoOrVideo ( name: String,text: String, image: String?,

                                     format_image: String, video: String?, format_video: String

    ): HttpResponse {

        val requestBody = mapOf(
            "name" to name,
            "text" to text,
            "image" to image,
            "format_image" to format_image,
            "video" to video,
            "format_video" to format_video
        )
        return try {

            val response = client.post("https://delta.online/api/lenta") {

                contentType(ContentType.Application.Json)

                setBody(requestBody)
            }
            println("Создание элемента ленты: ${response.toString()}")
            response
        } catch (e: Exception) {
            println("Ошибка создания элемента ленты: Error - ${e.message}")
            throw e
        }
    }

    /*name*: 'ewgweg'
text: ''
image: base64
format_image: 'jpg'
video: base64
format_video: 'mp4'*/

}