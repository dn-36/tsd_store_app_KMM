package com.project.network.organizations_network

import com.project.network.httpClientEngine
import com.project.network.organizations_network.model.Response
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
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object OrganizationsApi {

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

        // Получение списка организаций
        suspend fun getOrganizations(): List<Response> {
            val response = client.get("https://delta.online/api/company")
            println(" ////////////////////++++++++++")
            println(" ${response}")
            println(" ////////////////////++++++++++")
            return response.body<List<Response>>()
        }

    // Функция для установки активной организации
    suspend fun setActiveOrganization(organizationUi: String): Boolean {
        val response: HttpResponse = client.post("https://delta.online/api/active-company") {
            contentType(ContentType.Application.Json)
            setBody(organizationUi)
        }

        // Проверяем успешность запроса
        return response.status == HttpStatusCode.OK
    }
}