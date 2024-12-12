package com.project.network.characterisrics_products_network.model

import kotlinx.serialization.Serializable

@Serializable
data class Characteristic(
    val id: Int?,
    val name: String?,
    val created_at: String?,
    val updated_at: String?,
    val langs: List<Lang>?
)

