package com.project.network.group_entity_network.model

import kotlinx.serialization.Serializable

@Serializable
data class GroupEntityResponse(
    val id: Int,
    val name: String,
    val company_id: Int,
    val ui: String,
    val created_at: String,
    val updated_at: String,
    val entits: List<Entity>
)

@Serializable
data class Entity(
    val id: Int,
    val own: Int,
    val creater_id: Int? = null,
    val contragent_id: Int,
    val company_id: Int,
    val name: String,
    val inn: String,
    val kpp: String,
    val okpo: String,
    val ogrn: String,
    val base: String,
    val ur_address: String,
    val fact_address: String,
    val fio: String? = null,
    val phone: String? = null,
    val email: String? = null,
    val nds: Int? = null,
    val print: String? = null,
    val sign: String? = null,
    val book_sign: String? = null,
    val ui: String,
    val file_verify: String? = null,
    val verify: Int,
    val created_at: String,
    val updated_at: String,
    val user_id: Int? = null,
    val laravel_through_key: Int
)
