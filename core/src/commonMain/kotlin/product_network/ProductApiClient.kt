package product_network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import product_network.model.ApiResponse
import product_network.model.Product


// Определяем общий клиент для мультиплатформенного проекта
expect val httpClientEngine: HttpClientEngine

class ProductApiClient(private val token: String) {

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

    // Функция для выполнения GET запроса с заданными параметрами и извлечения полей name
    suspend fun getProductNames(): List<Product>{
        val url = "https://delta.online/api/products-filter"
        val products: ApiResponse = client.get(url) {
            parameter("cat", "0")
            parameter("active", "0")
            parameter("service", "2")
            parameter("sale", "true")
            parameter("order", "false")
            parameter("store", "false")
        }.body()
        return products.data?: listOf()//!!.map { it.name?:"" }
    }

    // Закрываем клиент после использования
    fun close() {
        client.close()
    }
}

