package com.project.network.product_network.model

import kotlinx.serialization.Serializable

@Serializable
data class Division(
    val id: Int,
    val company_id: Int,
    val name: String,
    val ui: String,
    val created_at: String,
    val updated_at: String,
    val local_id: Int,
    val store_id: Int?,
    val laravel_through_key: Int
)
