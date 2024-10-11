package com.project.network.chats_network

import kotlinx.serialization.Serializable

@Serializable
data class ChatsResponse(
    val name: String,
    val ui: String,
    val count_new_message: Int,
    val image: String? = null,
    val users: List<User>,
    val project: Project? = null,
    val lastMessageUi: String,
    val user_ui: String,
    val user_image: String? = null,
    val message: String,
    val data: Long,
    val created_at: String
)

@Serializable
data class User(
    val phone: String,
    val name: String,
    val ui: String,
    val active: Int,
    val image: String? = null,
    val id: Int,
    val laravel_through_key: Int
)

@Serializable
data class Project(
    val id: Int,
    val name: String,
    val creater_id: Int,
    val company_id: Int,
    val created_at: String,
    val updated_at: String,
    val entity_id: Int? = null,
    val active: Int,
    val entity_client_id: Int? = null
)