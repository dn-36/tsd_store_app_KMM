package com.project.network.arrival_goods.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateProduct(
    val text: String,
    val contragent_id: Int,
    val contragent_push_id: Int,
    val entity_id: Int,
    val entity_puhs_id: Int,
    val store_id: Int,
    val is_push: Int,
    val products: List<Product>
)
