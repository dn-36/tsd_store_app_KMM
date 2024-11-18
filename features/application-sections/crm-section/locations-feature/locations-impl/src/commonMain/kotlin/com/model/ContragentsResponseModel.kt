package com.model

data class ContragentsResponseModel(

    val id: Int?,

    val name: String?,

    val ui: String?,

    val entities: List<EntityContragentsModel>

)

data class EntityContragentsModel(

    val id: Int?,

    val name: String?,

    val ui: String?

)