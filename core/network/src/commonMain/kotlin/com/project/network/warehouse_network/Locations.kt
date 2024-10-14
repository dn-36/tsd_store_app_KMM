package com.project.network.warehouse_network

data class Locations(
    val address: String?,
    val companyId: Int?,
    val contragentId: Int?,
    val createrId: Int?,
    val id: Int?,
    val name: String?,
    val point: String?,
    val stores: List<Warehouse>? = null,
)
