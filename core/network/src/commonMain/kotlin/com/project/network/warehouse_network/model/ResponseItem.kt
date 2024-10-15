package com.project.network.warehouse_network.model

import com.project.network.warehouse_network.WarehouseApi
import kotlinx.serialization.Serializable

@Serializable
data class ResponseItem(
    val id: Int?,
    val adres: String?,
    val name: String?,
    val contragent: Contragent?,
    val default: Int?,
    val ui: String?,
    val email: String?,
    val entity: Entity?,
    val phone: String?,
    @Serializable(with = WarehouseApi.PointSerializer::class)
    val point: List<Double>?,
    val text: String?,
    val workers: List<Worker>?
)
