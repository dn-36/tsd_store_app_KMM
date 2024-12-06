package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model


data class StoreResponseArrivalAndConsumption(

    val id: Int?,

    val text: String,

    val store_id: Int?,

    val contragent_id: Int?,

    val ui: String?,

    val is_push: Int?,

    val contragent_push_id: Int?,

    val entity_id: Int?,

    val entity_push_id: Int?,

    val products: List<ProductDataArrivalAndConsumption?>?,

    val store: StoreInfoArrivalAndConsumption?,

    val created_at: String?,

    val contragent: ContragentInfoArrivalAndConsumption?,

    )

data class StoreInfoArrivalAndConsumption(

    val id: Int?,

    val name: String?,

)

data class ProductDataArrivalAndConsumption(

    val product_id: Int?,

    val count: Double?,

)

data class ContragentInfoArrivalAndConsumption(

    val id: Int?,

    val name: String?,

)
