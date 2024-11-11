package com.project.network.projects_control_network

import com.project.network.group_entity_network.GroupEntityClient
import com.project.network.group_entity_network.model.GroupEntityResponse
import com.project.network.httpClientEngine
import com.project.network.projects_control_network.model.ProjectControlResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
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

class ProjectControlClient {

    companion object{
        private var _token: String = ""
    }
    fun init(token: String): ProjectControlClient {
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

    // получение всех контролей проектов

    suspend fun getProjectControl(): ProjectControlResponse {

        val response = client.get("https://delta.online/api/controll-projects") {

        }
        println(" ////////////////////++++++++++")
        println("контроль проектов:  ${response}")
        println(" ////////////////////++++++++++")
        return response.body<ProjectControlResponse>()
    }

    // Создание нового контроля проектов
    suspend fun createProjectControl(text:String, data: String, time: String,
    project_id: String ): HttpResponse {

        val requestBody = mapOf(
            "text" to text,
            "data" to data,
            "time" to time,
            "project_id" to project_id,
        )
        return try {
            val response = client.post("https://delta.online/api/controll-projects") {
                contentType(ContentType.Application.Json)
                setBody(requestBody)
            }
            println("Создание контроля проектов: ${response.toString()}")
            response
        } catch (e: Exception) {
            println("CREATE ProjectControl: Error - ${e.message}")
            throw e
        }
    }

    // обновление контроля проектов
    suspend fun updateProjectControl( ui: String, text:String, data: String, time: String,
                                     project_id: String ): HttpResponse {

        val requestBody = mapOf(
            "text" to text,
            "data" to data,
            "time" to time,
            "project_id" to project_id,
        )
        return try {
            val response = client.put("https://delta.online/api/controll-projects/${ui}") {
                contentType(ContentType.Application.Json)
                setBody(requestBody)
            }
            println("Обновление контроля проектов: ${response.toString()}")
            response
        } catch (e: Exception) {
            println("UPDATE ProjectControl: Error - ${e.message}")
            throw e
        }
    }

}