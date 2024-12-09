package com.arrival_and_consumption_goods.model

data class WarehouseArrivalAndConsumption(

    val stores: List<StoreArrivalAndConsumption?>,

    val name:String?

)

data class StoreArrivalAndConsumption (

    val id: Int?,

    val name: String?,

    val ui: String? = null

)

