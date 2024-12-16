package product_network

import com.project.network.product_network.model.ProductCreate
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
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
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonPrimitive
import product_network.model.ApiResponse
import com.project.network.product_network.model.Product


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

    // получение инфо. по конкретному товару или услуге

    suspend fun getSpecificProduct ( ui: String ): Product {

        val response = client.get("https://delta.online/api/product/${ui}") {

        }
        println(" ////////////////////++++++++++")
        println("получение конкретной услги или товара:  ${response}")
        println(" ////////////////////++++++++++")
        return response.body<Product>()
    }

    // Создание нового товара или услуга
    suspend fun createGoodOrService ( name: String,
                                      video_youtube: String,
                                      ediz_id: Int?,
                                      category_id: Int?,
                                      is_product: Int?,
                                      is_sale: Int?,
        //min_count_store: 0 (int минимальный отстаток)
        //is_only_industry: 0/1 (только производство)
                                      system_category_id: Int?,
                                      is_view_sale: Int?,
                                      is_order: Int?,
                                      is_store: Int?,
                                      is_store_view: Int?,
                                      is_test: Int?,
                                      is_arenda: Int?,
                                      is_zakaz: Int?,
                                      is_ves: Int?,
                                      is_serial_nomer: Int?,
                                      is_date_fabrica: Int?,
                                      is_markirovka: Int?,
                                      is_bu: Int,
        //is_ob_zvonok: 0/1 (обратный звонок по товару)
        //metka_system: '' (Системная метка)
                                      sku: String,
                                      text_image: String,
                                      creater: String,
                                      nomer_creater: String,
                                      postavka: String,
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
            is_test = 1,
            is_arenda = 1,
            is_zakaz = 1,
            is_ves = 1,
            is_serial_nomer = 0,
            is_date_fabrica = 0,
            is_markirovka = 0,
            is_bu = is_bu,
            sku = sku,
            text_image = text_image,
            creater = creater,
            nomer_creater = nomer_creater,
            postavka = postavka,
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

            println("Создание товара или услуги: ${response.status}")

            println("Тело запроса при ошибке: - ${requestBody}")

            response

        } catch (e: Exception) {

            println("Ошибка при создании товара или услуги: - ${e.message}")

            println("Тело запроса при ошибке: - ${requestBody}")

            throw e
        }
    }

    // Обновление товара или услуги

    suspend fun updateGoodOrService ( id: Int, name: String,
                                      video_youtube: String,
                                      ediz_id: Int?,
                                      category_id: Int?,
                                      is_product: Int?,
                                      is_sale: Int?,
        //min_count_store: 0 (int минимальный отстаток)
        //is_only_industry: 0/1 (только производство)
                                      system_category_id: Int?,
                                      is_view_sale: Int?,
                                      is_order: Int?,
                                      is_store: Int?,
                                      is_store_view: Int?,
                                      is_test: Int?,
                                      is_arenda: Int?,
                                      is_zakaz: Int?,
                                      is_ves: Int?,
                                      is_serial_nomer: Int?,
                                      is_date_fabrica: Int?,
                                      is_markirovka: Int?,
                                      is_bu: Int,
        //is_ob_zvonok: 0/1 (обратный звонок по товару)
        //metka_system: '' (Системная метка)
                                      sku: String,
                                      text_image: String,
                                      creater: String,
                                      nomer_creater: String,
                                      postavka: String,
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
            is_test = is_test,
            is_arenda = is_arenda,
            is_zakaz = is_zakaz,
            is_ves = is_ves,
            is_serial_nomer = is_serial_nomer,
            is_date_fabrica = is_date_fabrica,
            is_markirovka = is_markirovka,
            is_bu = is_bu,
            sku = sku,
            text_image = text_image,
            creater = creater,
            nomer_creater = nomer_creater,
            postavka = postavka,
            price = price,
            tags = emptyList(),  // Явно указываем, что это пустой список строк
            variantes = emptyList(),  // То же самое
            divisions = divisions,
            image_upload = image_upload
        )

        return try {

            val response = client.put("https://delta.online/api/product/${id}") {

                contentType(ContentType.Application.Json)

                setBody(requestBody)
            }
            println("Обновление товара или услуги: ${response.toString()}")
            response
        } catch (e: Exception) {
            println("ошибка обновление товара или услуги: Error - ${e.message}")
            throw e
        }
    }

    // удаление продукта или услуги
    suspend fun deleteGoodOrService( id: Int ): HttpResponse {

        return try {

            val response = client.delete("https://delta.online/api/product/${id}") {

                contentType(ContentType.Application.Json)
            }

            println("Удаление товара или услуги: ${response.toString()}")

            response

        } catch (e: Exception) {

            println("ошибка удаления товара или услуги: Error - ${e.message}")

            throw e
        }
    }

    // Закрываем клиент после использования
    fun close() {
        client.close()
    }
}

