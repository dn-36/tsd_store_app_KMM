package com.project.network.warehouse_network.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int? = null // Поля для продуктов можно дополнить при необходимости
)
