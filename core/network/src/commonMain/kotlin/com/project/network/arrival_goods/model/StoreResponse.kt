package com.project.network.arrival_goods.model

import kotlinx.serialization.Serializable

@Serializable
data class StoreResponse(
    val id: Int,
    val company_id: Int,
    val store_id: Int,
    val contragent_id: Int,
    val text: String?,
    val log: String?,
    val status: String,
    val ui: String,
    val is_push: Int,
    val created_at: String,
    val updated_at: String,
    val contragent_push_id: Int,
    val entity_id: Int,
    val entity_push_id: Int,
    val products: List<ProductData>,
    val store: StoreInfo,
    val contragent: ContragentInfo,
    val contragent_push: ContragentInfo,
    val entity: EntityInfo,
    val entity_push: EntityInfo
)
