package com.project.network.crm_network

import com.project.network.crm_network.model.ApiResponseCRMOutgoing
import com.project.network.crm_network.model.ServiceItem
import com.project.network.httpClientEngine
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.append
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.InternalAPI
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

    // получение всех входящих crm

     suspend fun getIncomingCRM(): List<ApiResponseCRMOutgoing> {

        val response = client.get("https://delta.online/api/crm-other-company") {

        }
        println(" ////////////////////++++++++++")
        println("Входящие CRM:  ${response}")
        //println("Итог:  ${response.bodyAsText()}")
        println(" ////////////////////++++++++++")
        return response.body<List<ApiResponseCRMOutgoing>>()
    }

    // получение всех исходящих crm
    suspend fun getOutgoingCRM(): List<ApiResponseCRMOutgoing> {

        val response = client.get("https://delta.online/api/crm?type=1&service=%5B%5D&my=0&delete=0&search=&active=0") {

        }
        println(" ////////////////////++++++++++")
        println("Исходящие CRM:  ${response}")
        //println("Итог:  ${response.bodyAsText()}")
        println(" ////////////////////++++++++++")
        return response.body<List<ApiResponseCRMOutgoing>>()
    }

    // создание услуги
    suspend fun createCRM(

        serviceId: Int?,
        statusPay: Int?,
        verifyPay: Int?,
        task: String?,
        toLocalId: Int?,
        groupEntityId: Int?,
        fromLocalId: Int?,
        status: String?,
        price: String?,
        arendaId: Int?,
        specificationId: Int?,
        projectId: Int?,
        entityId: Int?,
        ourEntityId: Int?,
        text: String?,
        statusId: Int?,
        items: List<ServiceItem>, // Список элементов услуги

    ): String {
        return try {

            val parametrs = Parameters.build {
                append("service_id", serviceId?.toString() ?: "")
                append("status_pay", statusPay?.toString() ?: "0")
                append("verify_pay", verifyPay?.toString() ?: "0")
                append("task", task ?: "")
                append("to_local_id", toLocalId?.toString() ?: "")
                append("group_entity_id", groupEntityId?.toString() ?: "")
                append("from_local_id", fromLocalId?.toString() ?: "")
                append("status", status ?: "")
                append("price", price ?: "")
                append("arenda_id", arendaId?.toString() ?: "")
                append("specification_id", specificationId?.toString() ?: "")
                append("project_id", projectId?.toString() ?: "")
                append("entity_id", entityId?.toString() ?: "")
                append("our_entity_id", ourEntityId?.toString() ?: "")
                append("text", text ?: "")
                append("statusid", statusId?.toString() ?: "1")
                // Добавляем элементы из списка items с индексами
                items.forEachIndexed { index, item ->
                    append("name[$index]", item.name)
                    append("type_id[$index]", item.type_id.toString())
                }
            }


            // Отправляем JSON
            val response: HttpResponse = client.post("https://delta.online/api/crm") {
                contentType(ContentType.Application.FormUrlEncoded) // Указываем Content-Type
                setBody(FormDataContent(parametrs))
            }

            println("Создание crm: ${parametrs}")

            println("Создание crm: ${response}")

            response.bodyAsText() // Возвращаем ответ от сервера
        } catch (e: Exception) {
            println("Error: ${e.message}")
            e.message.toString() // Возвращаем сообщение об ошибке
        }

    }

    suspend fun updateCRM(

        ui: String,
        serviceId: Int?,
        statusPay: Int?,
        verifyPay: Int?,
        task: String?,
        to_local_id: Int?,
        group_entity_id: Int?,
        from_local_id: Int?,
        status: String?,
        price: String?,
        arendaId: Int?,
        specificationId: Int?,
        projectId: Int?,
        entityId: Int?,
        ourEntityId: Int?,
        text: String?,
        statusId: Int?,
        items: List<ServiceItem>, // Список элементов услуги
    ): String {
        return try {

            val parametrs = Parameters.build {
                append("service_id", serviceId?.toString() ?: "")
                append("status_pay", statusPay?.toString() ?: "0")
                append("verify_pay", verifyPay?.toString() ?: "0")
                append("task", task ?: "")
                append("to_local_id", to_local_id?.toString() ?: "")
                append("group_entity_id", group_entity_id?.toString() ?: "")
                append("from_local_id", from_local_id?.toString() ?: "")
                append("status", status ?: "")
                append("price", price ?: "")
                append("arenda_id", arendaId?.toString() ?: "")
                append("specification_id", specificationId?.toString() ?: "")
                append("project_id", projectId?.toString() ?: "")
                append("entity_id", entityId?.toString() ?: "")
                append("our_entity_id", ourEntityId?.toString() ?: "")
                append("text", text ?: "")
                append("statusid", statusId?.toString() ?: "1")
                // Добавляем элементы из списка items с индексами
                items.forEachIndexed { index, item ->
                    append("name[$index]", item.name)
                    append("type_id[$index]", item.type_id.toString())
                }
                append("_method", "put")
            }

            // Отправляем JSON
            val response: HttpResponse = client.put("https://delta.online/api/crm/${ui}") {
                contentType(ContentType.Application.FormUrlEncoded) // Указываем Content-Type
                setBody(FormDataContent(parametrs))
            }

            println("Обновление crm ${parametrs}")

            println("Обновление crm ${response}")


            response.bodyAsText() // Возвращаем статус ответа

        } catch (e: Exception) {

            e.message.toString() // Возвращаем сообщение об ошибке
        }
    }

}