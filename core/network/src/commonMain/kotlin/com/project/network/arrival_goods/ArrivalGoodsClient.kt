package com.project.network.arrival_goods

import com.project.network.ConstData
import com.project.network.arrival_goods.model.CreateRequest
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
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class ArrivalGoodsClient {

    /*CreateInExData(contragentId=8, contragentPushId=7, entityId=8, entityPushId=7, storeId=39, isPush=1, products=[ProductCreteWrapper(product=ProductCreateCounter(id=189, count=15))])*/

    /*CreateRequest(text=Text, contragent_id=93, contragent_push_id=103, entity_id=86, entity_push_id=86, store_id=39, is_push=1, products=[Product(id=169, count=50)])*/

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
    // POST запрос для создания записи о приходе товаров

    suspend fun createProduct(
        text: String,
        contragentId: Int,
        contragentPushId: Int,
        entityId: Int,
        entityPushId: Int,
        storeId: Int,
        isPush: Int,
        products: List<Product>
    ): String {
        return try {
            val requestBody = CreateRequest(
                text = text,
                contragent_id = contragentId,
                contragent_push_id = contragentPushId,
                entity_id = entityId,
                entity_push_id = entityPushId,
                store_id = storeId,
                is_push = isPush,
                products = products
            )

            val response: HttpResponse = client.post("https://delta.online/api/local-store-push-or-pull") {
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

    @Serializable
    data class Product(

        val product:ProductInfo

    )

    @Serializable
    data class ProductInfo(

        val id: Int,

        val count: Int

    )

    // Обновление товара
    suspend fun updateProduct(productUi: String, updatedData: CreateRequest): HttpResponse {
        return try {
            val response = client.put("https://delta.online/api/local-store-push-or-pull/$productUi") {
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
    suspend fun deleteProduct(ui: String): HttpResponse {
        return try {
            val response = client.delete("https://delta.online/api/local-store-push-or-pull/$ui")
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