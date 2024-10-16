package com.project.domain.repository

import kotlinx.coroutines.CoroutineScope

interface OrganizationClientApi {

    suspend fun createOrganization (name: String, url: String)

}