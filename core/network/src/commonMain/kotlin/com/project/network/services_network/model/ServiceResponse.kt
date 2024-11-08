package com.project.network.services_network.model

import kotlinx.serialization.Serializable

@Serializable
data class ServiceResponse(
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
    val image: String? = null,
    val users: List<User>? = listOf(),
    val items: List<ServiceItem>?,
    val contragents: List<Contragent>? = listOf(),
    val contragents_ishod: List<Contragent>? = listOf(),
    val divisions: List<Division>? = listOf(),
    val groups: List<Group>? = listOf(),
    val check_entity: Entity? = null,
    val check_group_entity: GroupEntity? = null,
    val our_users: List<User>? = listOf(),
    val users_entits: List<UserEntity>? = listOf(),
    val default_entity: Entity? = null,
    val limit_group_entity: GroupEntity? = null
)

@Serializable
data class ServiceItem(
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

@Serializable
data class User(
    val id: Int? = null,
    val name: String? = null
    // Define other fields if available
)

@Serializable
data class Contragent(
    val id: Int? = null,
    val name: String? = null
    // Define other fields if available
)

@Serializable
data class Division(
    val id: Int,
    val company_id: Int?,
    val name: String?,
    val ui: String?,
    val created_at: String?,
    val updated_at: String?,
    val local_id: Int? = null,
    val store_id: Int? = null,
    val laravel_through_key: Int?
)

@Serializable
data class Group(
    val id: Int? = null,
    val name: String? = null
    // Define other fields if available
)

@Serializable
data class Entity(
    val id: Int? = null,
    val name: String? = null
    // Define other fields if available
)

@Serializable
data class GroupEntity(
    val id: Int? = null,
    val name: String? = null
    // Define other fields if available
)

@Serializable
data class UserEntity(
    val id: Int? = null,
    val name: String? = null
    // Define other fields if available
)
