package com.project.network.crm_network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ApiResponseCRMOutgoing(
    val id: Int?,
    val service_id: Int,
    val company_id: Int?,
    val project_id: Int?,
    val organization_id: Int?,
    val user_id: Int?,
    val active_company_id: Int?,
    val company_work: String?,
    val arenda_id: Int?,
    val status: String?,
    val text: String?,
    val ui: String?,
    val active: JsonElement? = null,
    val flesh: Int?,
    val del: Int?,
    val nomer: Int?,
    val statusid: Int?,
    val data: String?,
    val date_start: String?,
    val date_stop: String?,
    val price: Int?,
    val template: Int?,
    val status_pay: String?,
    val template_value: String?,
    val task: String?,
    val created_at: String,
    val updated_at: String,
    val entity_id: Int?,
    val group_entity_id: Int?,
    val check: Int?,
    val log_check: String?,
    val check_our: Int?,
    val log_check_our: Int?,
    val our_entity_id: Int?,
    val cafe_type: Int?,
    val resource_id: Int?,
    val cafe_id: Int?,
    val invoice_pay: String?,
    val type_pay: Int?,
    val specification_id: Int?,
    val account_entity_id: Int?,
    val verify_pay: String?,
    val system: Int?,
    val search_worker: Int?,
    val no_view_client: Int?,
    val is_order_creater: Int?,
    val to_local_id: Int?,
    val from_local_id: Int?,
    val projects: Project?,
    val service: Service?,
    val company: Company?,
    val companactiv: String?,
    val entity: Entity?,
    val groupentits: GroupEntity?,
    val entity_our: EntityOur?,
    val specs: Specs?
)

@Serializable
data class Service(
    val id: Int?,
    val name: String?,
    val text: String?,
    val doc: String?,
    val ui: String?,
    val system: Int?,
    val width: Int?,
    val height: Int?,
    val service_type_doc: Int?,
    val comp_project: String?,
    val view: Int?,
    val view_comp_project: String?,
    val store: Int?,
    val delete_status: Int?,
    val company_id: Int?,
    val created_at: String?,
    val updated_at: String?,
    val need_check: Int?,
    val check_entity_id: Int?,
    val check_group_entity_id: Int?,
    val need_check_our: Int?,
    val limit_group_entity_id: Int?,
    val default_entity_id: Int?,
    val background: String?,
    val image: String?,
    val items_value: List<ItemValue>?,
    val all_check_users_id: List<Int>?,
    val all_check_users_other: List<String>?
)

@Serializable
data class ItemValue(
    val id: Int?,
    val service_id: Int?,
    val name: String?,
    val type: String?,
    val metka: String?,
    val search: Int?,
    val req: Int?,
    val created_at: String?,
    val updated_at: String?
)

@Serializable
data class Company(
    val id: Int?,
    val name: String?,
    val creater_id: Int?,
    val company_id: Int?,
    val created_at: String?,
    val updated_at: String?,
    val own: Int?
)

@Serializable
data class EntityOur(
    val id: Int?,
    val own: Int?,
    val creater_id: Int?,
    val contragent_id: Int?,
    val company_id: Int?,
    val name: String?,
    val inn: String?,
    val kpp: String?,
    val okpo: String?,
    val ogrn: String?,
    val base: String?,
    val ur_address: String?,
    val fact_address: String?,
    val fio: String?,
    val phone: String?,
    val email: String?,
    val nds: String?,
    val print: String?,
    val sign: String?,
    val book_sign: String?,
    val ui: String?,
    val file_verify: String?,
    val verify: Int?,
    val created_at: String?,
    val updated_at: String?,
    val user_id: Int?,
    val company: Company?
)

@Serializable
data class Specs(
    val id: Int?,
    val company_id: Int?,
    val text: String?
)

@Serializable
data class Entity(
    val id: Int?,
    val own: Int?,
    val creater_id: Int?,
    val name: String?
    // другие возможные поля...
)

data class Activee(
    val id: Int,
    val service_id: Int
)

@Serializable
data class Project(
    val id: Int,
    val name: String
)

@Serializable
data class GroupEntity(
    val id: Int,
    val name: String
)