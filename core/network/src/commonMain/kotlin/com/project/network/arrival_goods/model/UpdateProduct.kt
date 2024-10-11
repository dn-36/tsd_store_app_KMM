package com.project.network.arrival_goods.model

import com.project.network.arrival_goods.model.Product
import kotlinx.serialization.Serializable

@Serializable
data class UpdateProduct(
    val id: Int,
    val text: String? = null,  // Поля могут быть опциональными в зависимости от того, что ты хочешь обновить
    val products: List<Product>? = null
)
