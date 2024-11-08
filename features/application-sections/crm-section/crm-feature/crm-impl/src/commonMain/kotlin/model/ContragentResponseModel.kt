package model

data class ContragentResponseModel(

val id: Int?,

val name: String?,

val ui: String?,

val own: Int?,

val entities: List<EntityContragentModel>?

)

data class EntityContragentModel(

    val id: Int?,

    val name: String?,

    val ui: String?,

)
