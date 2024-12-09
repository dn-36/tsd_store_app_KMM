package product_network

import com.project.network.product_network.model.ProductCreate
import com.project.network.projects_control_network.model.ProjectControlResponse
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
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
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
    suspend fun getProductNames(): List<Product> {
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

    // получение всех продуктов и услуг

    suspend fun getAllProducts(): List<Product> {

        val response = client.get("https://delta.online/api/product") {

        }
        println(" ////////////////////++++++++++")
        println("получение всех продуктов и услуг:  ${response}")
        println(" ////////////////////++++++++++")
        return response.body<List<Product>>()
    }

    // Создание нового товара или услуга
    suspend fun createGoodOrService ( name: String,
                                      video_youtube: String,
                                      ediz_id: Int?,
                                      category_id: Int?,
                                      is_product: Int,
                                      is_sale: Int,
        //min_count_store: 0 (int минимальный отстаток)
        //is_only_industry: 0/1 (только производство)
                                      system_category_id: Int?,
                                      is_view_sale: Int,
                                      is_order: Int,
                                      is_store: Int,
                                      is_store_view: Int,
        //is_test: 0/1 (Можно взять на тест)
        //is_arenda: 0/1 (Можно взять в аренду)
        //is_zakaz: 0/1 (Можно заказать)
        //is_ves: 0/1 (Весовой товар)
        //is_serial_nomer: 0/1 (Учет по серийному номеру)
        //is_date_fabrica: 0/1 (Учитывать дату производства)
        //is_markirovka: 0/1 (Маркированный товар)
        //is_bu: 0/1 (Б/у или нет)
        //is_ob_zvonok: 0/1 (обратный звонок по товару)
        //metka_system: '' (Системная метка)
                                      sku: String,
                                      text_image: String,
        //creater: '' (Производитель)
        //nomer_creater: '' (Номер произовдителя)
        //postavka: '' (Срок поставки)
        //url: '' (slug для ссылки на английском )
                                      price: Float?,
                                      tags: List<String>, //(пока что пустой массив отправлять)
                                      variantes: List<String>, //(пока что пустой массив отправлять)
                                      divisions: String, //(пока что пустой строкой отправлять)
        image_upload: String?

        ): HttpResponse {

        val requestBody = ProductCreate(
            name = name,
            video_youtube = video_youtube,
            ediz_id = ediz_id,
            category_id = category_id,
            is_product = is_product,
            is_sale = is_sale,
            system_category_id = system_category_id,
            is_view_sale = is_view_sale,
            is_order = is_order,
            is_store = is_store,
            is_store_view = is_store_view,
            sku = sku,
            text_image = text_image,
            price = price,
            tags = emptyList(),  // Явно указываем, что это пустой список строк
            variantes = emptyList(),  // То же самое
            divisions = divisions,
            image_upload = image_upload
        )

        return try {

            val response = client.post("https://delta.online/api/product") {

                contentType(ContentType.Application.Json)

                setBody(requestBody)
            }

            println("Создание товара или услуги: ${response.toString()}")

            response

        } catch (e: Exception) {

            println("Ошибка при создании товара или услуги: - ${e.message}")

            println("Тело запроса при ошибке: - ${requestBody}")

            throw e
        }
    }

    // Закрываем клиент после использования
    fun close() {
        client.close()
    }
}

