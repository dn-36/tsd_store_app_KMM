package com.model

data class WarehouseModel(

    val stores: List<StoreModel?>,

    val name:String?

)

data class StoreModel (

    val id: Int?,

    val name: String?,

    val ui: String? = null

)