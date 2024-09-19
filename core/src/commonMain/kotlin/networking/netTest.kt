package networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.json.defaultSerializer
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


// Определяем общий клиент для мультиплатформенного проекта
expect val httpClientEngine: HttpClientEngine

class ApiClient(private val token: String) {

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
    suspend fun getProductNames(): List<String>{
        val url = "https://delta.online/api/products-filter"
        val products: ApiResponse = client.get(url) {
            parameter("cat", "0")
            parameter("active", "0")
            parameter("service", "2")
            parameter("sale", "true")
            parameter("order", "false")
            parameter("store", "false")
        }.body()
        return products.data!!.map { it.name?:"" }
    }

    // Закрываем клиент после использования
    fun close() {
        client.close()
    }
}
@Serializable
data class ApiResponse(
    val current_page: Int?,
    val data: List<Product>?
)

@Serializable
data class Product(
    val id: Int?,
    val name: String?,
    val text: String?,
    val price: Float?,
    val image: String?,
    val category_id: Int?,
    val creater_id: Int?,
    val company_id: Int?,
    val created_at: String?,
    val updated_at: String?,
    val active_pay_id: Int?,
    val ediz_id: Int?,
    val is_product: Int?,
    val is_sale: Int?,
    val is_view_sale: Int?,
    val sale_price: Int?,
    val is_order: Int?,
    val is_store: Int?,
    val is_store_view: Int?,
    val is_ob_zvonok: Int?,
    val is_test: Int?,
    val is_arenda: Int?,
    val is_zakaz: Int?,
    val is_ves: Int?,
    val is_serial_nomer: Int?,
    val is_date_fabrica: Int?,
    val is_markirovka: Int?,
    val metka_system: String?,
    val sku: String?,
    val text_image: String?,
    val creater: String?,
    val nomer_creater: String?,
    val postavka: String?,
    val url: String?,
    val is_bu: Int?,
    val is_division_all: Int?,
    val ui: String?,
    val identificator: Int?,
    val is_delete: Int?,
    val is_only_industry: Int?,
    val min_count_store: Int?,
    val video: String?,
    val video_youtube: String?,
    val summ_stock: Int?,
    val summ_reserve: Int?,
    val summ_order: Int?,
    val codes: List<String>?,
    val seo: Seo?,
    val ediz: Ediz?,
    val local_store: List<LocalStore>?,
    val category: Category?,
    val connections: List<Connection>?
)

@Serializable
data class Seo(
    val id: Int?,
    val product_id: Int?,
    val title: String?,
    val desc: String?,
    val keys: String?,
    val text: String?,
    val text_to: String?,
    val gross_text: String?,
    val created_at: String?,
    val updated_at: String?
)

@Serializable
data class Ediz(
    val id: Int?,
    val company_id: Int?,
    val name: String?,
    val ui: String?,
    val created_at: String?,
    val updated_at: String?
)

@Serializable
data class LocalStore(
    val id: Int?,
    val store_id: Int?,
    val product_id: Int?,
    val stock: Int?,
    val reserve: Int?,
    val order: Int?,
    val created_at: String?,
    val updated_at: String?,
    val local: Local?
)

@Serializable
data class Local(
    val id: Int?,
    val name: String?,
    val ui: String?,
    val company_id: Int?,
    val local_id: Int?,
    val created_at: String?,
    val updated_at: String?,
    val default: Int?
)

@Serializable
data class Category(
    val id: Int?,
    val name: String?,
    val creater_id: Int?,
    val company_id: Int?,
    val created_at: String?,
    val updated_at: String?
)

@Serializable
data class Connection(
    val id: Int? = null
)