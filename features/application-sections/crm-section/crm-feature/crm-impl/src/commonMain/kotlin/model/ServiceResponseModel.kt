package model

import kotlinx.serialization.Serializable

data class ServiceResponseModel(

    val id: Int,
    val name: String?,
    val text: String? = null,
    val doc: String? = null,
    var ui: String?,
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
    val image: String? = null,
    val items: List<ServiceItemModel>?

    )

data class ServiceItemModel(
    val id: Int,
    val service_id: Int?,
    val name: String?,
    val type: String?,
    val metka: String? = null,
    val search: Int?,
    val req: Int?,
    val created_at: String?,
    val updated_at: String?
)
