package screens.model

data class ContragentResponseModel (

        val id: Int?,

        val name: String?,

        val ui: String?,

        val own: Int?,

        val entits: List<EntityModel>?


)

data class EntityModel(

    val id: Int?,

    val name: String?,

    val ui: String?,

    )