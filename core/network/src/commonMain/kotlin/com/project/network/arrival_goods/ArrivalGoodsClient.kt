package com.project.network.arrival_goods

import com.project.network.ConstData
import com.project.network.arrival_goods.model.CreateRequest
import com.project.network.arrival_goods.model.ProductArrival
import com.project.network.arrival_goods.model.StoreResponse
import com.project.network.httpClientEngine
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.delete
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

class ArrivalGoodsClient {

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

    // Получение списка товаров (приход)
    suspend fun getProducts(): List<StoreResponse> {

        val response = client.get("https://delta.online/api/local-store-push-or-pull")

        println("Получен ответ: $response")

        return response.body<List<StoreResponse>>()
    }

    // Добавление нового товара (приход/расход)
    suspend fun createProduct (text: String, contragent_expense_id: Int, contragent_push_id: Int,

                               entity_expense_id: Int, entity_push_id: Int, store_id: Int,

                               is_push: Int, products: List<ProductArrival>): HttpResponse {
        return try {

            val response = client.post("https://delta.online/api/local-store-push-or-pull") {

                contentType(ContentType.Application.Json)  // Установка типа контента

                setBody( CreateRequest ( text = text, contragent_expense_id = contragent_expense_id,

                    contragent_push_id = contragent_push_id, entity_expense_id = entity_expense_id,

                    entity_push_id = entity_push_id, store_id = store_id,

                    is_push = is_push, products = products))
            }

            println("Товар успешно добавлен: ${response.toString()}")

            response

        } catch (e: Exception) {

            println("CREATE Product: Error - ${e.message}")

            throw e
        }
    }

    // Обновление товара
    suspend fun updateProduct(productId: Int, updatedData: CreateRequest): HttpResponse {
        return try {
            val response = client.put("https://delta.online/api/local-store-push-or-pull/ui/$productId") {
                contentType(ContentType.Application.Json) // Установка типа контента
                setBody(updatedData)
            }
            println("Товар успешно обновлен: ${response.toString()}")
            response
        } catch (e: Exception) {
            println("UPDATE Product: Error - ${e.message}")
            throw e // Или обработайте ошибку по-другому
        }
    }

    // Удаление товара
    suspend fun deleteProduct(productId: Int): HttpResponse {
        return try {
            val response = client.delete("https://delta.online/api/local-store-push-or-pull/ui/$productId")
            println("Товар успешно удален: ${response.toString()}")
            response
        } catch (e: Exception) {
            println("DELETE Product: Error - ${e.message}")
            throw e
        }
    }

    // Закрытие HTTP-клиента
    fun close() {
        client.close()
    }
}