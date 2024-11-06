package com.project.network.crm_network

import com.project.network.crm_network.model.ApiResponseCRMOutgoing
import com.project.network.crm_network.model.CreateCRM
import com.project.network.crm_network.model.ServiceItem
import com.project.network.httpClientEngine
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
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
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
        to_local_id:Int?,
        group_entity_id:Int?,
        from_local_id:Int?,
        status: String?,
        price: String?,
        arendaId: Int?,
        specificationId: Int?,
        projectId: Int?,
        entityId: Int?,
        ourEntityId: Int?,
        text: String?,
        statusId: Int?,
        items: List<ServiceItem>
    ): String {
        return try {
            val requestBody = CreateCRM(
                service_id = serviceId,
                status_pay = statusPay,
                verify_pay = verifyPay,
                task = task,
                to_local_id = to_local_id,
                group_entity_id = group_entity_id,
                from_local_id = from_local_id,
                status = status,
                price = price,
                arenda_id = arendaId,
                specification_id = specificationId,
                project_id = projectId,
                entity_id = entityId,
                our_entity_id = ourEntityId,
                text = text,
                statusid = statusId,
                items = items
            )

            val response: HttpResponse = client.post("https://delta.online/api/crm") {
                contentType(ContentType.Application.Json)
                setBody(requestBody) // Используем setBody для передачи сериализованного объекта
            }

            println("333")
            println("333")
            println("${requestBody}")
            println("333")
            println("333")

            response.body() // Возвращаем статус ответа
        } catch (e: Exception) {
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
        items: List<ServiceItem>
    ): String {
        return try {
            val requestBody = CreateCRM(
                service_id = serviceId,
                status_pay = statusPay,
                verify_pay = verifyPay,
                task = task,
                to_local_id = to_local_id,
                group_entity_id = group_entity_id,
                from_local_id = from_local_id,
                status = status,
                price = price,
                arenda_id = arendaId,
                specification_id = specificationId,
                project_id = projectId,
                entity_id = entityId,
                our_entity_id = ourEntityId,
                text = text,
                statusid = statusId,
                items = items
            )

            val response: HttpResponse = client.put("https://delta.online/api/crm/${ui}") {
                contentType(ContentType.Application.Json)
                setBody(requestBody) // Используем setBody для передачи сериализованного объекта
            }

            println("444")
            println("444")
            println("${response}")
            println("444")
            println("444")

            response.body() // Возвращаем статус ответа
        } catch (e: Exception) {
            e.message.toString() // Возвращаем сообщение об ошибке
        }
    }

}