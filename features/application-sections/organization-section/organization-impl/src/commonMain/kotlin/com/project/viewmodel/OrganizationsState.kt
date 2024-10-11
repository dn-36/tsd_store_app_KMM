package com.project.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.project.network.organizations_network.model.Response

data class OrganizationsState(

    val allOrganizations: List<Response> = emptyList(),

    val iUsed: MutableState<Boolean> = mutableStateOf(true)

)
