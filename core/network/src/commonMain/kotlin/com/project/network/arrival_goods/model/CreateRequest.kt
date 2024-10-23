package com.project.network.arrival_goods.model
import com.project.network.arrival_goods.ArrivalGoodsClient
import kotlinx.serialization.Serializable

@Serializable
data class CreateRequest(
    val text: String,
    val contragent_id: Int,
    val contragent_push_id: Int,
    val entity_id: Int,
    val entity_push_id: Int, // Поправил здесь опечатку
    val store_id: Int,
    val is_push: Int,
    val products: List<ArrivalGoodsClient.Product>
)
