package com.project.network.locations_network.model

import kotlinx.serialization.Serializable

@Serializable
data class Contragent(
    val id: Int?,
    val name: String?,
    val ui: String?,
    val own: Int?
)
