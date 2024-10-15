package com.project.network.warehouse_network.model

import kotlinx.serialization.Serializable

@Serializable
data class Contragent(
    val id: Int?,
    val name: String?,
    val ui: String?,
    val own: Int?
)
