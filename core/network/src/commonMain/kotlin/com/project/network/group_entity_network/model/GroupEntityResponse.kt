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
