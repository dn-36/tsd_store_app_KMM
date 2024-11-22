package model

data class UnitResponseModel(

    val id: Int,
    val company_id: Int? = null,
    val name: String?,
    val ui: String,
    val created_at: String,
    val updated_at: String

)
