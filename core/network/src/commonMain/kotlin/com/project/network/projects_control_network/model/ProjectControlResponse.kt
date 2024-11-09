package com.project.network.projects_control_network.model

import kotlinx.serialization.Serializable

fun removeHtmlTags(input: String): String {
    // Регулярное выражение для поиска HTML-тегов
    return input.replace(Regex("<[^>]*>"), "").trim()
}

@Serializable
data class ProjectControlResponse(
    val data: List<Service>?,
    val balans: Int?
)

@Serializable
data class Service(
    val id: Int?,
    val user_id: Int?,
    val project_id: Int?,
    val text: String?,
    val price: Int?,
    val data: String?,
    val time: String?,
    val created_at: String?,
    val updated_at: String?,
    val company_id: Int?,
    val type: String?,
    val project: Project?,
    val user: User?
)

@Serializable
data class Project(
    val id: Int?,
    val name: String?,
    val creater_id: Int?,
    val company_id: Int?,
    val created_at: String?,
    val updated_at: String?,
    val entity_id: Int?,
    val active: Int?,
    val entity_client_id: Int?,
    val entity: Entity?
)

@Serializable
data class Entity(
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
    val user_id: Int?
)

@Serializable
data class User(
    val id: Int?,
    val name: String?,
    val email: String?,
    val email_verified_at: String?,
    val phone: String?,
    val ui: String?,
    val policy: Int?,
    val created_at: String?,
    val updated_at: String?,
    val tema: String?,
    val active: Int?,
    val inn: String?,
    val image: String?,
    val contragents: Int?,
    val price: Int?,
    val lang_id: Int?
)
