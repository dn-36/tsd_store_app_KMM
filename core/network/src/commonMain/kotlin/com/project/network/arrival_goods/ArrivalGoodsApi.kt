package com.project.network.arrival_goods

import com.project.network.arrival_goods.model.CreateProduct
import com.project.network.arrival_goods.model.GetProduct
import com.project.network.arrival_goods.model.UpdateProduct
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
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ArrivalGoodsApi {

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

        // Получение списка товаров (приход)
        suspend fun getProducts(): List<GetProduct> {
            val response = client.get("https://delta.online/api/local-store-push-or-pull")
            println(" ////////////////////++++++++++")
            println(" ${response}")
            println(" ////////////////////++++++++++")
            return response.body<List<GetProduct>>()
        }

        // Добавление нового товара (приход/расход)
        suspend fun createProduct(product: CreateProduct): HttpResponse {
            return try {
                val response = client.post("https://delta.online/api/local-store-push-or-pull") {
                    contentType(ContentType.Application.Json)  // Установка типа контента
                    setBody(product)
                }
                println(" ${response.toString()} ")
                response
            }
            catch (e: Exception){
                println("CREATE Product: Error - ${e.message}")
                throw e
            }
        }

        // Обновление товара
        suspend fun updateProduct(productId: Int, updatedProduct: UpdateProduct): HttpResponse {
            return try {
                val response = client.put("https://delta.online/api/local-store-push-or-pull/ui") {
                    contentType(ContentType.Application.Json) // Установка типа контента
                    setBody(updatedProduct)
                }
                println(" ${response.toString()} ")
                response
            } catch (e: Exception) {
                println("UPDATE Product: Error - ${e.message}")
                throw e // Или обработайте ошибку по-другому
            }
        }

        // Удаление товара
        suspend fun deleteProduct(productId: Int): HttpResponse {
            return client.delete("https://delta.online/api/local-store-push-or-pull/ui") {
                parameter("id", productId)
            }
        }

        // Закрытие HTTP-клиента
        fun close() {
            client.close()
        }
}