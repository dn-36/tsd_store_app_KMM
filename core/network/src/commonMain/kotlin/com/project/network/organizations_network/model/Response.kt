package com.project.network.organizations_network.model

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val id: Int?,
    val active: Int?,
    val user_id: Int?,
    val company_id: Int?,
    val created_at: String?,
    val updated_at: String?,
    val verify: Int?,
    val company: Company?
)
