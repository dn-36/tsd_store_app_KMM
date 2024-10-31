package model

data class ServiceResponseModel(

    val id: Int,
    val name: String?,
    val text: String? = null,
    val doc: String? = null,
    val ui: String?,
    val system: Int?,
    val width: Int?,
    val height: Int?,
    val service_type_doc: Int?,
    val comp_project: List<String>? = null,
    val view: Int?,
    val view_comp_project: List<String>? = null,
    val store: Int?,
    val delete_status: Int?,
    val company_id: Int?,
    val created_at: String?,
    val updated_at: String?,
    val need_check: Int?,
    val check_entity_id: Int? = null,
    val check_group_entity_id: Int? = null,
    val need_check_our: Int?,
    val limit_group_entity_id: Int? = null,
    val default_entity_id: Int? = null,
    val background: String?,
    val image: String? = null
)
