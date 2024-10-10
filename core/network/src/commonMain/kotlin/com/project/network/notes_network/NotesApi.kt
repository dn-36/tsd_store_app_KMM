package com.project.network.notes_network

import com.project.network.httpClientEngine
import com.project.network.notes_network.model.BodyNoteDto
import com.project.network.notes_network.model.Note
import com.project.network.notes_network.model.NoteResponse
import com.project.network.notes_network.model.User
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

object NotesApi {

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

    // Получение списка заметок
    suspend fun getNotes(): List<NoteResponse> {


        val response = client.get("https://delta.online/api/notes") {
            parameter("active", 1)
            // для получения принятых пользователем заметок
        }
        println(" ////////////////////++++++++++")
        println(" ${response}")
        println(" ////////////////////++++++++++")
        return response.body<List<NoteResponse>>()
    }

    //получение всех пользователей
    suspend fun getUsers(): List<User> {

        val response = client.get("https://delta.online/api/users-company") {

        }
        println(" ////////////////////++++++++++")
        println(" ${response}")
        println(" ////////////////////++++++++++")
        return response.body<List<User>>()
    }

    // Добавление новой заметки
    suspend fun createNote(note: Note): HttpResponse {
        return try {
            val response = client.post("https://delta.online/api/notes") {
                contentType(ContentType.Application.Json)  // Установка типа контента
                setBody(note)
            }
            println(" ${response.toString()} ")
            response
        }
        catch (e: Exception){
            println("CREATE Note: Error - ${e.message}")
            throw e
        }
    }
    // Обновление заметки
    suspend fun updateNote(noteId: String, updatedNote: BodyNoteDto): HttpResponse {
        println("////////////////////////////////////////")
        return try {
            val response = client.put("https://delta.online/api/notes/$noteId") {
                contentType(ContentType.Application.Json) // Установка типа контента
                setBody(updatedNote)
            }
            println(" ${response.toString()} ")
            response
        } catch (e: Exception) {
            println("UPDATE Note: Error - ${e.message}")
            throw e // Или обработайте ошибку по-другому
        }
    }

    // Принять заметку
    suspend fun acceptNote(noteId: String): HttpResponse {
        return client.post("https://delta.online/api/notes-active-view/$noteId")
    }

    // Удаление заметки
    suspend fun deleteNote(noteId: String): HttpResponse {
        return client.delete("https://delta.online/api/notes/$noteId")
    }

    // Закрытие HTTP-клиента
    fun close() {
        client.close()
    }


}