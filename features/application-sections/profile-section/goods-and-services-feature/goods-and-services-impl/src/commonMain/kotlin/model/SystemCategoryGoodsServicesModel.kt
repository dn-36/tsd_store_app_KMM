package model

data class SystemCategoryGoodsServicesModel(
    val id: Int?,
    val name: String?,
    val ui: String?,
    val created_at: String,
    val updated_at: String,
    val parametrs: List<ParameterModel> = emptyList()
)

data class ParameterModel(
    val id: Int?,
    val name: String?,
    val created_at: String,
    val updated_at: String,
    val laravel_through_key: Int?
)