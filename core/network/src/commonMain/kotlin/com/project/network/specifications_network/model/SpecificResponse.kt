package com.project.network.specifications_network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class SpecificResponse(
    val id: Int,
    val company_id: Int?,
    val text: String?,
    val ui: String,
    val price: Int?,
    val status: Int?,
    val valuta_id: Int? = null,
    val created_at: String?,
    val updated_at: String,
    val local_store_id: Int? = null,
    val entity_id: Int?,
    val specification_id: Int? = null,
    val spec_item: List<SpecItem>?
)

@Serializable
data class SpecItem(
    val id: Int,
    val spec_id: Int?,
    val product_id: Int?,
    val price_id: Int? = null,
    val block: String?,
    val count: Int?,
    val price: Double?,
    val nds: Int? = null,
    val text: String? = null,
    val created_at: String?,
    val updated_at: String?,
    val cafe_send: Int?,
    val spec_item_id: Int? = null,
    val numb: Int? = null,
    val work: Int,
    val sale: Int? = null,
    val product: Product?
)

@Serializable
data class Product(
    val id: Int,
    val name: String?,
    val text: String? = null,
    val price: Double?,
    val image: String?,
    val category_id: Int?,
    val creater_id: Int?,
    val company_id: Int?,
    val created_at: String?,
    val updated_at: String?,
    val active_pay_id: Int? = null,
    val ediz_id: Int?,
    val is_product: Int?,
    val is_sale: Int?,
    val is_view_sale: Int?,
    val sale_price: Int? = null,
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
    val metka_system: String? = null,
    val sku: String? = null,
    val text_image: String? = null,
    val creater: String? = null,
    val nomer_creater: String? = null,
    val postavka: String? = null,
    val url: String?,
    val is_bu: Int?,
    val is_division_all: Int?,
    val ui: String,
    val identificator: Int?,
    val is_delete: Int?,
    val is_only_industry: Int?,
    val min_count_store: Int?,
    val video: String? = null,
    val video_youtube: String? = null,
    val video_mobile: String? = null,
    val system_category_id: Int? = null,
    val productprice: JsonElement? = null,
    val ediz: Ediz?,
    val category: Category?,
    val local_store: List<LocalStore>? = listOf()
)

@Serializable
data class Ediz(
    val id: Int,
    val company_id: Int? = null,
    val name: String?,
    val ui: String,
    val created_at: String?,
    val updated_at: String?
)

@Serializable
data class Category(
    val id: Int,
    val name: String?,
    val creater_id: Int?,
    val company_id: Int?,
    val created_at: String?,
    val updated_at: String?,
    val url: String?,
    val ui: String,
    val category_langs: List<CategoryLang>?
)

@Serializable
data class CategoryLang(
    val id: Int,
    val name: String?,
    val lang_id: Int?,
    val created_at: String?,
    val updated_at: String?,
    val category_id: Int?
)

@Serializable
data class LocalStore(
    val id: Int,
    val store_id: Int?,
    val product_id: Int?,
    val stock: Int?,
    val reserve: Int?,
    val order: Int?,
    val created_at: String?,
    val updated_at: String?,
    val local: Local?,
    val serials: JsonElement? = null
)

@Serializable
data class Local(
    val id: Int,
    val name: String?,
    val ui: String,
    val company_id: Int?,
    val local_id: Int? = null,
    val created_at: String?,
    val updated_at: String?,
    val default: Int?
)

