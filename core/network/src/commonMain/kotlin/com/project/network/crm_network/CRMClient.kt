package com.project.network.crm_network

import com.project.network.crm_network.model.ApiResponseCRM
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

class CRMClient {

    companion object{
        private var _token: String = ""
    }
    fun init(token: String): CRMClient {
        _token = token
        return this
    }

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
            header("Authorization", "Bearer ${_token}")
        }
    }

    // получение всех входящих crm

     suspend fun getIncomingCRM(): List<ApiResponseCRM> {

        val response = client.get("https://delta.online/api/crm-other-company") {

        }
        println(" ////////////////////++++++++++")
        println("Входящие CRM:  ${response}")
        println(" ////////////////////++++++++++")
        return response.body<List<ApiResponseCRM>>()
    }

    // получение всех исходящих crm
    suspend fun getOutgoingCRM(): String {

        val response = client.get("https://delta.online/api/сrm?type=1&service=[]&my=0&delete=0&search=&active=0") {

        }
        println(" ////////////////////++++++++++")
        println("Исходящие CRM:  ${response}")
        println(" ////////////////////++++++++++")
        return response.bodyAsText()
    }

}