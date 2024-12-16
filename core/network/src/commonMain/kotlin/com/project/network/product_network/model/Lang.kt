package com.project.network.product_network.model

import kotlinx.serialization.Serializable

@Serializable
data class Lang(
    val id: Int,
    val name: String?,
    val parametrs_id: Int?,
    val lang_id: Int?,
    val created_at: String,
    val updated_at: String
)
