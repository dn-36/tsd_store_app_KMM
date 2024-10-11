package com.project.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.project.network.organizations_network.model.Response

data class OrganizationsState(

    val allOrganizations: List<Response> = emptyList(),

    val isUsed: MutableState<Boolean> = mutableStateOf(true),

    val listColorActiveOrganizations:MutableList<Color> = mutableListOf()

)
