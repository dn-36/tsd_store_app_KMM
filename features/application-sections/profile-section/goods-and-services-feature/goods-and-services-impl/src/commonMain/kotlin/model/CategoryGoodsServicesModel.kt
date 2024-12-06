package model

data class CategoryGoodsServicesModel(
    val id: Int?,
    val name: String?,
    val creater_id: Int?,
    val company_id: Int?,
    val created_at: String,
    val updated_at: String,
    val url: String?,
    val ui: String?,
    val category_langs: List<CategoryLangGoodsServicesModel>?
)

data class CategoryLangGoodsServicesModel(
    val id: Int?,
    val name: String?,
    val lang_id: Int?,
    val created_at: String,
    val updated_at: String,
    val category_id: Int?
)
