package com.project.domain.repository

import kotlinx.coroutines.CoroutineScope

interface OrganizationClientApi {

    suspend fun createOrganization (name: String, url: String, scope: CoroutineScope,
                                    onCreate: () -> Unit)

    suspend fun deleteOrganization (ui: String, scope: CoroutineScope)
    suspend fun choosingActiveOrganization (ui: String, scope: CoroutineScope)

}