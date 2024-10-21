package com.project.network.arrival_goods.model

import kotlinx.serialization.Serializable


@Serializable
data class ProductData(
    val id: Int?,
    val store_action_id: Int?,
    val product_id: Int?,
    val count: Int?,
    val created_at: String?,
    val updated_at: String?,
    val product: ProductDetails?
)
