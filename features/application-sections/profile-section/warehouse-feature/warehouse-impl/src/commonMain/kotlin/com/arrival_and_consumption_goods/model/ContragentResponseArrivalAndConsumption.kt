package com.arrival_and_consumption_goods.model


data class ContragentResponseArrivalAndConsumption(

    val id: Int?,

    val name: String?,

    val ui: String?,

    val own: Int?,

    val entits: List<EntityArrivalAndConsumption>?

)

data class EntityArrivalAndConsumption(

    val id: Int?,

    val name: String?,

    val ui: String?,

)
