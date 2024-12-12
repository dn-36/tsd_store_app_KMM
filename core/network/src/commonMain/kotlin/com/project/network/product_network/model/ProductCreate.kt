package com.project.network.product_network.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductCreate(
    val name: String,
    val video_youtube: String,
    val ediz_id: Int?,
    val category_id: Int?,
    val is_product: Int?,
    val is_sale: Int?,
    val system_category_id: Int?,
    val is_view_sale: Int?,
    val is_order: Int?,
    val is_store: Int?,
    val is_store_view: Int?,
    val is_test: Int?,
    val is_arenda: Int?,
    val is_zakaz: Int?,
    val is_ves: Int?,
    val is_serial_nomer: Int?,
    val is_date_fabrica: Int?,
    val is_markirovka: Int?,
    val is_bu: Int,
    val sku: String,
    val text_image: String,
    val creater: String,
    val nomer_creater: String,
    val postavka: String,
    val price: Float?,
    val tags: List<String>,
    val variantes: List<String>,
    val divisions: String,
    val image_upload: String?
)
