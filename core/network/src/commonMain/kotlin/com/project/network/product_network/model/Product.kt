package com.project.network.product_network.model

import kotlinx.serialization.Serializable
import product_network.ProductApiClient
import product_network.model.Category
import product_network.model.Connection
import product_network.model.Ediz
import product_network.model.LocalStore
import product_network.model.Seo

//summ_stock, summ_reserve, summ_order, codes, local_store

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
    val summ_stock: Double? = null,
    @Serializable(with = ProductApiClient.CountSerializer::class) val summ_reserve: Double? = null,
    @Serializable(with = ProductApiClient.CountSerializer::class) val summ_order: Double? = null,
    val codes: List<String>? = emptyList(),
    val seo: Seo?,
    val ediz: Ediz?,
    val local_store: List<LocalStore>? = emptyList(),
    val category: Category?,
    val connections: List<Connection>?,
    val pays: List<String> = emptyList(),
    val prices: List<String> = emptyList(),
    val tags: List<String> = emptyList(),
    val components: List<String> = emptyList(),
    val completes: List<String> = emptyList(),
    val variantes: List<Variant> = emptyList(),
    val divisions: List<Division> = emptyList(),
    val videos: List<String> = emptyList(),
    val parametrs: List<Parametr> = emptyList(),
    val images: List<String> = emptyList(),
    val files: List<String> = emptyList()
)

