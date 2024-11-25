package com.project.network.projects_network

import com.project.network.common.httpClientEngine
import com.project.network.projects_network.model.ProjectResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ProjectClient {

    companion object{
        private var _token: String = ""
    }
    fun init(token: String): ProjectClient {
        _token = token
        return this
    }

    private val client = HttpClient(httpClientEngine) {
        install(ContentNegotiation) {
            json(Json {
               // ignoreUnknownKeys = true // Игнорировать неизвестные поля
                isLenient = true// Быть гибким с форматом JSON
            })
        }
        install(Logging) {
            level = LogLevel.BODY // Включить логирование для отладки
        }
        defaultRequest {
            header("Authorization", "Bearer ${_token}")
        }
    }

    // получение всех проектов

    suspend fun getProjects (): List<ProjectResponse> {

        val response = client.get("https://delta.online/api/project?active=1&all=0") {

        }
        println(" ////////////////////++++++++++")
        println("получение проектов :  ${response.bodyAsText()}")
        println(" ////////////////////++++++++++")
        return response.body<List<ProjectResponse>>()
    }

}