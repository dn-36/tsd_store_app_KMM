package model

data class CharacteristicModel(
    val id: Int?,
    val name: String?,
    val created_at: String?,
    val updated_at: String?,
    val langs: List<LangModel>?
)

data class LangModel(
    val id: Int?,
    val name: String?,
    val parametrs_id: Int?,
    val lang_id: Int?,
    val created_at: String?,
    val updated_at: String?
)

