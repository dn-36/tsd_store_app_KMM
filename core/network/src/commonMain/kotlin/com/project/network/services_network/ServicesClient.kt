package com.project.network.services_network

import com.project.network.crm_network.CRMClient
import com.project.network.httpClientEngine
import com.project.network.services_network.model.ServiceResponse
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

class ServicesClient {

    companion object{

        private var _token: String = ""

    }
    fun init(token: String): ServicesClient {

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

    //Получение услуг

    suspend fun getServices(): List<ServiceResponse> {

        val response = client.get("https://delta.online/api/crm-services") {

        }
        println(" ////////////////////++++++++++")
        println("Получение Услуг:  ${response}")
        println(" ////////////////////++++++++++")
        return response.body<List<ServiceResponse>>()
    }

}