package com.project.network.arrival_goods.model

import kotlinx.serialization.Serializable

@Serializable
data class StoreInfo(
    val id: Int?,
    val name: String?,
    val ui: String?,
    val company_id: Int?,
    val local_id: Int?,
    val created_at: String?,
    val updated_at: String?,
    val default: Int?
)
