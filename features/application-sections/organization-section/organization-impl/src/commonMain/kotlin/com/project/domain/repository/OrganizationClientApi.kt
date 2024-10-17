package com.project.domain.repository

import androidx.compose.ui.graphics.Color
import com.project.network.organizations_network.model.Response

interface OrganizationClientApi {

    suspend fun createOrganization (name: String, url: String, onCreate: () -> Unit)

    suspend fun deleteOrganization (ui: String, onDelete: () -> Unit)

    suspend fun choosingActiveOrganization (ui: String, onChoosing: () -> Unit)
    suspend fun getOrganizations (onSet: (listColor:MutableList<Color>, listOrganization:List<Response> ) -> Unit)

}