package com.project.network.warehouse_network.model

import kotlinx.serialization.Serializable


@Serializable
data class Warehouse(
    val id: Int?,
    val adres: String?,
    val name: String?,
    val default: Int?,
    val ui: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val point: String? = null,
    val text: String? = null,
    val stores: List<Store?> = emptyList()
)
