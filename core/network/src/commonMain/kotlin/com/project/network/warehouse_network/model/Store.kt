package com.project.network.warehouse_network.model

import kotlinx.serialization.Serializable

@Serializable
data class Store(
    val id: Int,
    val name: String,
    val default: Int,
    val ui: String? = null,
    val local: Local? = null,
    val products: List<Product> = emptyList()
)
