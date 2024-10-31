package com.project.network.projects_network

import com.project.network.crm_network.CRMClient
import com.project.network.crm_network.model.ApiResponseCRMOutgoing
import com.project.network.httpClientEngine
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

class ProjectsClient {

    companion object{
        private var _token: String = ""
    }
    fun init(token: String): ProjectsClient {
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

    suspend fun getProjects (): String {

        val response = client.get("https://delta.online/api/project") {

        }
        println(" ////////////////////++++++++++")
        println("получение проектов :  ${response.bodyAsText()}")
        println(" ////////////////////++++++++++")
        return response.bodyAsText()
    }

}