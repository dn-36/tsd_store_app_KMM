package com.project.network.organizations_network.model

import kotlinx.serialization.Serializable

@Serializable
data class ActiveOrganizationRequest(
    val ui: String
)
