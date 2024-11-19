package com.project.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.project.network.organizations_network.model.Response

data class OrganizationsState(

    val listOrganizations: List<Response> = emptyList(),

    val listFilteredOrganizations: List<Response> = emptyList(),

    val isUsed: MutableState<Boolean> = mutableStateOf(true),

    val isVisibilityDeleteComponent: Float = 0f,

    val isUpdateOrganization: Boolean = false,

    val updateOrganization: Response? = null,

    val listColorBorderTf: List<Color> = listOf ( Color.LightGray, Color.LightGray ),

    val listColorActiveOrganizations:MutableList<Color> = mutableListOf(),

    )
