package product_network.model

import com.project.network.arrival_goods.ArrivalGoodsClient
import kotlinx.serialization.Serializable
import product_network.ProductApiClient


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
    val video_mobile: String?,
    val system_category_id: String?,
    val summ_stock: Double?,
    @Serializable(with = ProductApiClient.CountSerializer::class) val summ_reserve: Double?,
    @Serializable(with = ProductApiClient.CountSerializer::class) val summ_order: Double?,
    val codes: List<String>?,
    val seo: Seo?,
    val ediz: Ediz?,
    val local_store: List<LocalStore>?,
    val category: Category?,
    val connections: List<Connection>?
)

@Serializable
data class Parametr(
    val id: Int,
    val parametrs_id: Int,
    val product_id: Int,
    val created_at: String,
    val updated_at: String,
    val name: String?,
    val parametr: ParametrDetails
)

@Serializable
data class ParametrDetails(
    val id: Int,
    val name: String,
    val created_at: String,
    val updated_at: String,
    val unit_id: Int?,
    val langs: List<Lang>,
    val unit: String?
)

@Serializable
data class Lang(
    val id: Int,
    val name: String?,
    val parametrs_id: Int,
    val lang_id: Int,
    val created_at: String,
    val updated_at: String
)