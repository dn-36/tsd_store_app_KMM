package com.project.network.specifications_network

import com.project.network.httpClientEngine
import com.project.network.specifications_network.model.Items
import com.project.network.specifications_network.model.SpecificResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.delete
import io.ktor.client.request.forms.FormDataContent
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
import kotlinx.serialization.json.Json

class SpecificationsClient {

    companion object{
        private var _token: String = ""
    }
    fun init(token: String): SpecificationsClient {
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

    //Получение спецификаций

    suspend fun getSpecifications(): List<SpecificResponse> {

        val response = client.get("https://delta.online/api/specification") {

        }
        println(" ////////////////////++++++++++")
        println("Получение Спецификаций:  ${response}")
        println(" ////////////////////++++++++++")
        return response.body<List<SpecificResponse>>()
    }

    // создание спецификацию
    suspend fun createSpecification(

        text: String?,
        valuta_id: Int?,
        local_store_id: Int?,
        price: Int?,
        status: Int?,
        items: List<Items>, // Список элементов услуги

    ): String {

        return try {

            val parametrs = Parameters.build {
                append("text", text?.toString() ?: "")
                append("valuta_id", valuta_id?.toString() ?: "")
                append("local_store_id", local_store_id?.toString() ?: "")
                append("price", price?.toString() ?: "")
                append("status", status?.toString() ?: "")
                // Добавляем элементы из списка items с индексами
                items.forEachIndexed { index, item ->
                    append("product_id[$index]", item.product_id?.toString() ?: "")
                    append("count[$index]", item.count?.toString() ?: "")
                    append("block[$index]", item.block?.toString() ?: "")
                    append("spectext[$index]", item.spectext?.toString() ?: "")
                    append("price_item[$index]", item.price_item?.toString() ?: "")
                    append("price_id[$index]", "".toString() ?: "")
                    append("nds[$index]", item.nds?.toString() ?: "")
                }
            }


            // Отправляем JSON
            val response: HttpResponse = client.post("https://delta.online/api/specification") {
                contentType(ContentType.Application.FormUrlEncoded) // Указываем Content-Type
                setBody(FormDataContent(parametrs))
            }

            println("Тело Создание спецификации: ${parametrs}")

            println("Ответ Создание спецификации: ${response.bodyAsText()}")

            response.bodyAsText() // Возвращаем ответ от сервера
        } catch (e: Exception) {

            println("////Error Создание спецификации: ${e.message}////")

            e.message.toString() // Возвращаем сообщение об ошибке
        }

    }

    // обновление спецификации
    suspend fun updateSpecification(

        ui:String,
        text: String?,
        valuta_id: Int?,
        local_store_id: Int?,
        price: Int?,
        status: Int?,
        items: List<Items>, // Список элементов услуги

    ): String {

        return try {

            val parametrs = Parameters.build {
                append("text", text?.toString() ?: "")
                append("valuta_id", valuta_id?.toString() ?: "")
                append("local_store_id", local_store_id?.toString() ?: "")
                append("price", price?.toString() ?: "")
                append("status", status?.toString() ?: "")
                // Добавляем элементы из списка items с индексами
                items.forEachIndexed { index, item ->
                    append("product_id[$index]", item.product_id?.toString() ?: "")
                    append("count[$index]", item.count?.toString() ?: "")
                    append("block[$index]", item.block?.toString() ?: "")
                    append("spectext[$index]", item.spectext?.toString() ?: "")
                    append("price_item[$index]", item.price_item?.toString() ?: "")
                    append("price_id[$index]", "".toString() ?: "")
                    append("nds[$index]", item.nds?.toString() ?: "")
                }
            }


            // Отправляем JSON
            val response: HttpResponse = client.put("https://delta.online/api/specification/${ui}") {
                contentType(ContentType.Application.FormUrlEncoded) // Указываем Content-Type
                setBody(FormDataContent(parametrs))
            }

            println("Тело обновления спецификации: ${parametrs}")

            println("Ответ обновление спецификации: ${response.bodyAsText()}")

            response.bodyAsText() // Возвращаем ответ от сервера
        } catch (e: Exception) {

            println("////Error обновление спецификации: ${e.message}////")

            e.message.toString() // Возвращаем сообщение об ошибке
        }

    }

    // удаление спецификации
    suspend fun deleteSpecification( ui: String ): HttpResponse {

        return try {

            val response = client.delete("https://delta.online/api/specification/${ui}") {

                contentType(ContentType.Application.Json)
            }

            println("Удаление спецификации: ${response.toString()}")

            response

        } catch (e: Exception) {

            println("DELETE спецификации: Error - ${e.message}")

            throw e
        }
    }

}