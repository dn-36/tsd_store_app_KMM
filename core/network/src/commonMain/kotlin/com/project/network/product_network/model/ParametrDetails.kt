package com.project.network.product_network.model

import kotlinx.serialization.Serializable

@Serializable
data class ParametrDetails(
    val id: Int?,
    val name: String?,
    val created_at: String,
    val updated_at: String,
    val unit_id: Int?,
    val langs: List<Lang>?,
    val unit: String?
)