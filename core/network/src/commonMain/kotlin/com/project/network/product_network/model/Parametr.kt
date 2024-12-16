package com.project.network.product_network.model

import kotlinx.serialization.Serializable

@Serializable
data class Parametr(
    val id: Int?,
    val parametrs_id: Int?,
    val product_id: Int?,
    val created_at: String,
    val updated_at: String,
    val name: String?,
    val parametr: ParametrDetails?
)
