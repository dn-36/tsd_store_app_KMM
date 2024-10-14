package com.project.network.warehouse_network

data class Warehouse(
val uid: String?,
val id: Int?,
val companyId: Int?,
val default: Int?,
val localId: Int?,
val name: String?,
val products: List<ProductInfo>? = null
)
