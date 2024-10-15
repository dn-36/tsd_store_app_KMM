package com.project.network.locations_network.model

import kotlinx.serialization.Serializable

@Serializable
data class Entity(
    val id: Int?,
    val name: String?,
    val ui: String?,
    val company: Company?,
    val own: Int?,
    val email: String?,
    val fact_address: String?,
    val kpp: String?,
    val inn: String?,
    val nds: String?,
    val ogrn: String?,
    val okpo: String?,
    val phone: String?,
    val ur_address: String?
)

