
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
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import util.NetworkError
import util.Result


class ChatsApi() {
    private val baseUrl:String = "https://delta.online/"
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

        val response = client.get(baseUrl+"api/chats") {
            parameter("active", 1)
        }
        println("!!!!!!!!!! ${response.body<List<ChatsResponse>>()[0].project?.id} !!!!!!!!!!!!!!!")
        return try{
            response.body()
        }catch (e:Exception){
            listOf()
        }
    }


    // Обновление заметки
    suspend fun getListMassengers(chatId: String): ChatResponseMessages {


            val response = client.get(baseUrl+"api/chats/${chatId}?page=1") {
                parameter("active", 1)

            }
            return response.body()

    }


    suspend fun readAllMesanger(uiChat: String,myNumber:String): Result<String,NetworkError> {

        return try {
            val listMassengers = (getListMassengers(uiChat).messages?.data?: listOf())

              val cutList:MutableList<MessageData?> =  mutableListOf()

            listMassengers.forEachIndexed { index, messageData ->
                if(
                  listMassengers.size <= 12
                ) cutList.add(messageData)
                  else
                  if(index in (listMassengers.size - 12)..listMassengers.size){
                      cutList.add(messageData)
                  }
            }

            cutList.forEach {
                if(myNumber!= it?.user?.phone?:"") {

                  println("!!!!\n" +
                          "${it?.user?.phone}"+
                          "\n\n " + client
                      .post(baseUrl+"view-message/${it?.ui?: ""}")
                      .status +
                          "\n" +
                          "\n !!!!"
                  )
                }
            }

           Result.Success("Result")

        } catch (e: Exception) {

            return Result.Error(NetworkError.SERVER_ERROR)
        }
    }

    @Serializable
    data class FileData(
        val data: String,
        val type: String,
        val filename: String
    )



    // Функция для отправки POST-запроса

    suspend fun sendMessage(
        chatUI: String,
        feedbackUI: String,
        text: String,
        imageBase64: String?,
        filesMobile: List<FileData>,
        filesData: List<ByteArray>?
    ): Result<String, NetworkError> {

        try {
            // Отправляем POST-запрос с multipart/form-data
            val response: HttpResponse = client.post(baseUrl+"api/message/") {

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
                                      //  append(HttpHeaders.ContentType, ContentType.Text.Plain)
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

            return Result.Success(response.bodyAsText())

        } catch (e: Exception) {
            return Result.Error(NetworkError.SERVER_ERROR)
        }
    }

    suspend fun createChat(
        name: String,
        imageBase64: String?,
        userPhones: List<String>,
        projectId: Int?
    ): String {

        val response = client.post(baseUrl + "api/chats") {
            contentType(ContentType.Application.Json)
            setBody(
                MultiPartFormDataContent(
                    formData {
                        append("name", name)
                        append("image", imageBase64 ?: "")
                        userPhones.forEach { phone ->
                            append("users[]", phone)
                        }
                            append("project_id", projectId?:0)
                    }
                )
            )
        }.body<HttpResponse>().bodyAsText()

        println("ASASASAS" + response)
        return response
    }

  suspend fun getProjects():String{
       return client.get(baseUrl+"api/project").body<HttpResponse>().bodyAsText()
   }

}
