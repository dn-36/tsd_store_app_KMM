package com.project.network.contragent_network.model

import kotlinx.serialization.Serializable

@Serializable
data class ContragentResponse(
    val id: Int?,
    val name: String?,
    val ui: String?,
    val own: Int?,
    val entits: List<Entity>?
)
