package com.project.network.organizations_network

import com.project.network.common.ConstData
import com.project.network.common.httpClientEngine
import com.project.network.organizations_network.model.ActiveOrganizationRequest
import com.project.network.organizations_network.model.CreateOrganizationRequest
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
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class  OrganizationsClient {

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
                header("Authorization", "Bearer ${ConstData.TOKEN}")
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

    // Функция для установки активной организации с обработкой ошибок
    suspend fun setActiveOrganization(organizationUi: String): Boolean {
        return try {
            val response: HttpResponse = client.post("https://delta.online/api/active-company") {
                contentType(ContentType.Application.Json)
                setBody(ActiveOrganizationRequest(ui = organizationUi))
            }
            println(" ////////////////////++++++++++")
            println("Смена активной организации ${response}")
            println(" ////////////////////++++++++++")
            // Проверяем успешность запроса
            response.status == HttpStatusCode.OK

        } catch (e: Exception) {
            // Логируем или выводим информацию об ошибке
            println("Ошибка при установке активной организации: ${e.message}")
            false
        }
    }

    // Функция для удаления организации
    suspend fun deleteOrganization(organizationUi: String): Boolean {
        return try {
            val response: HttpResponse = client.post("https://delta.online/api/delete-company") {
                contentType(ContentType.Application.Json)
                setBody(ActiveOrganizationRequest(ui = organizationUi))
            }
            println(" ////////////////////++++++++++")
            println("Удаление организации  ${response}")
            println(" ////////////////////++++++++++")
            // Проверяем успешность запроса
            response.status == HttpStatusCode.OK

        } catch (e: Exception) {
            // Логируем или выводим информацию об ошибке
            println("Ошибка при удалении организации : ${e.message}")
            false
        }
    }

    // Функция для создания организации
    suspend fun createOrganization(name: String, url: String?): Boolean {
        return try {
            val response: HttpResponse = client.post("https://delta.online/api/company") {
                contentType(ContentType.Application.Json)
                setBody(CreateOrganizationRequest(name, url))
            }
            println(" ////////////////////++++++++++")
            println("Создание организации  ${response}")
            println(" ////////////////////++++++++++")
            // Проверяем успешность запроса
            response.status == HttpStatusCode.OK

        } catch (e: Exception) {
            // Логируем или выводим информацию об ошибке
            println("Ошибка при добавлении организации : ${e.message}")
            false
        }
    }

    // Обновление организации
    suspend fun updateOrganization ( ui: String, updatedOrganization: CreateOrganizationRequest): HttpResponse {
        println("////////////////////////////////////////")
        return try {
            val response = client.put("https://delta.online/api/company/$ui") {
                contentType(ContentType.Application.Json) // Установка типа контента
                setBody(updatedOrganization)
            }
            println(" ${response.toString()} ")
            response
        } catch (e: Exception) {
            println("UPDATE Oragnization: Error - ${e.message}")
            throw e // Или обработайте ошибку по-другому
        }
    }

}