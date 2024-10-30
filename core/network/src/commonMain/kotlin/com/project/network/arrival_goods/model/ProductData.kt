package com.project.network.arrival_goods.model

import com.project.network.arrival_goods.ArrivalGoodsClient
import kotlinx.serialization.Serializable


@Serializable
data class ProductData(
    val id: Int?,
    val store_action_id: Int?,
    val product_id: Int?,
    @Serializable(with = ArrivalGoodsClient.CountSerializer::class) val count: Double?, // Используем кастомный сериализатор
    val created_at: String?,
    val updated_at: String?,
    val product: ProductDetails?
)
