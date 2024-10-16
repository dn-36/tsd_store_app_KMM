package com.project.network.locations_network.model

import kotlinx.serialization.Serializable


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