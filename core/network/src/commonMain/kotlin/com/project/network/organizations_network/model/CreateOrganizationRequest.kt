package com.project.network.organizations_network.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateOrganizationRequest(
    val name: String,
    val url: String?
)