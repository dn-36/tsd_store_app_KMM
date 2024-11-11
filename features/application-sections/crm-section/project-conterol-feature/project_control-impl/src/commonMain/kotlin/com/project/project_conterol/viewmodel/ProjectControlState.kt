package org.example.project.presentation.project_control.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.project.project_conterol.model.ProjectResponseModel
import com.project.project_conterol.model.ProjectsControlResponseModel
import com.project.project_conterol.model.ServiceModel

data class ProjectControlState(

    val listProjectsControl: ProjectsControlResponseModel? = null,

    val listProjects: List<ProjectResponseModel> = emptyList(),

    val isSet: Boolean = true,

    val isVisibilityDataEntryComponent: MutableState<Boolean> = mutableStateOf(false),

    val listExpendedDescription: List<Boolean> = emptyList(),

    val updatedItem: ServiceModel? = null
)
