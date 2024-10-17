package com.project.network.chats_network

import com.project.network.httpClientEngine
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.InternalAPI
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


class ChatsApi() {
   companion object{
       private var _token: String = ""
   }
    fun init(token: String):ChatsApi{
        _token = token
        return this
    }



    private val client = HttpClient(httpClientEngine) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true

            })
        }
        install(Logging) {
            level = LogLevel.BODY // Включить логирование для отладки
        }
        defaultRequest {
            header("Authorization", "Bearer $_token")
        }
    }

    // Получение списка заметок
    suspend fun getChats(): List<ChatsResponse> {
        //sendMessage(ConstData.TOKEN,)

        val response = client.get("https://delta.online/api/chats") {
            parameter("active", 1)
        }
        println("!!!!!!!!!!${response.body<String>()}!!!!!!!!!!!!!!!")
        return try{
            response.body()
        }catch (e:Exception){
            listOf()
        }
    }


    // Обновление заметки
    suspend fun getListMassengers(noteId: String): ChatResponseMessages {

        return try {
            val response = client.get("https://delta.online/api/chats/${noteId}?page=1") {
                parameter("active", 1)

            }
            println("::::::::::::::::::::;${response.body<ChatResponseMessages>()}:::::::::::::::::::::::::")
            response.body()
        } catch (e: Exception) {
            println("UPDATE Note: Error - ${e.message}")
          //  throw e // Или обработайте ошибку по-другому
            return ChatResponseMessages(
                0,
                "",
                "",
                "",
                "",
                0,
                "",
                "",
                0,
                null,
                listOf(),
                null,

            )
        }
    }

    @Serializable
    data class FileData(
        val data: String,
        val type: String,
        val filename: String
    )

    // Функция для отправки POST-запроса
    @OptIn(InternalAPI::class)
    suspend fun sendMessage(
        chatUI: String,
        feedbackUI: String,
        text: String,
        imageBase64: String?,
        filesMobile: List<FileData>,
        filesData: List<ByteArray>?
    ): String {


        try {
            // Отправляем POST-запрос с multipart/form-data
            val response: HttpResponse = client.post("https://delta.online/api/message/") {

                setBody(
                    MultiPartFormDataContent(
                        formData {
                            append("chat_ui", chatUI)
                            append("feedback_ui", feedbackUI)
                            append("text", text)

                            // Если изображение передано, добавляем его
                            imageBase64?.let {
                                append("image", it)
                            }

                            // Добавляем мобильные файлы
                            filesMobile.forEach { file ->
                                append(
                                    "filesmobile",
                                    file.data,
                                    Headers.build {
                                        append(HttpHeaders.ContentType, ContentType.Text.Plain)
                                        append(HttpHeaders.ContentDisposition, "filename=\"${file.filename}\"")
                                    }
                                )
                            }

                            // Добавляем файлы для multipart/form-data
                            filesData?.forEachIndexed { index, file ->
                                append(
                                    "filesdata",
                                    file,
                                    Headers.build {
                                        append(HttpHeaders.ContentType, "application/octet-stream")
                                        append(HttpHeaders.ContentDisposition, "filename=\"file_$index\"")
                                    }
                                )
                            }
                        }
                    )
                )
            }

            println("<<<<<<<${response.body<HttpResponse>().status}>>>>>>>>")
            return response.bodyAsText()

        } catch (e: Exception) {
            println("<<<<<<<${"Error: ${e.message}"}>>>>>>>>")
            return "Error: ${e.message}"
        } //finally {
           // client.close()
      // }
    }




}
