package com.project.network.units_measurement_network.model

import kotlinx.serialization.Serializable


@Serializable
data class UnitResponse(
    val id: Int,
    val company_id: Int? = null,
    val name: String?,
    val ui: String,
    val created_at: String,
    val updated_at: String
)
