package com.project.network.organizations_network.model

import kotlinx.serialization.Serializable

@Serializable
data class Valuta(
    val id: Int?,
    val company_id: Int? = null,
    val name: String?,
    val system_name: String?,
    val curs: Int?,
    val ui: String?,
    val active: Int?,
    val created_at: String? = null,
    val updated_at: String? = null
)
