package com.project.network.crm_network.model

import kotlinx.serialization.Serializable

@Serializable
data class Active(
    val id: Int,
    val service_id: Int,
)
