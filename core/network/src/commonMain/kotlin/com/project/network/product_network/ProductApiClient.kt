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
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonPrimitive
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

    object CountSerializer : KSerializer<Double?> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Count", PrimitiveKind.DOUBLE)

        override fun serialize(encoder: Encoder, value: Double?) {
            if (value != null) {
                encoder.encodeDouble(value) // Кодируем как Double
            } else {
                encoder.encodeNull() // Если значение null, кодируем как null
            }
        }

        override fun deserialize(decoder: Decoder): Double? {
            // Используем JsonDecoder для получения JsonPrimitive
            val jsonDecoder = decoder as? JsonDecoder ?: throw IllegalArgumentException("Expected JsonDecoder")
            val input = jsonDecoder.decodeJsonElement() as JsonPrimitive

            // Проверяем тип значения через методы JsonPrimitive
            return when {
                input.isString -> null // Если это строка, возвращаем null
                input.content.toDoubleOrNull() != null -> input.content.toDouble() // Преобразуем как Double
                else -> null // Если значение не число
            }
        }
    }

    // Закрываем клиент после использования
    fun close() {
        client.close()
    }
}

