package com.project.network.contragent_network.model

import com.project.network.contragent_network.model.Entity
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    val id: Int?,
    val name: String?,
    val ui: String?,
    val own: Int?,
    val entits: List<Entity>?
)
