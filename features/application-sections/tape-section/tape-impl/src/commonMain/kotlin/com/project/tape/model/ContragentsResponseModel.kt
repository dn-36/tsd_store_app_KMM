package com.project.tape.model

data class ContragentsResponseModel (

    val id: Int?,

    val name: String?,

    val ui: String?,

    val own: Int?,

    val entities: List<EntitiesModel>?


)

data class EntitiesModel(

    val id: Int?,

    val name: String?,

    val ui: String?,

    )