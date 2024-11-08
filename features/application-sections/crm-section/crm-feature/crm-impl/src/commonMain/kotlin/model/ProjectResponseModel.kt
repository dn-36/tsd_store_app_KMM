package model

data class ProjectResponseModel(

    val id: Int,
    val name: String,
    val creater_id: Int,
    val company_id: Int,
    val created_at: String,
    val updated_at: String,
    val entity_id: Int? = null,
    val active: Int,
    val entity_client_id: Int? = null

)
