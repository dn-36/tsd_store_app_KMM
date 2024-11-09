package org.example.project.presentation.project_control.viewmodel

import com.project.project_conterol.model.ProjectResponseModel
import com.project.project_conterol.model.ProjectsControlResponseModel

data class ProjectControlState(
    val projects:String = "",
    val date:String = "",
    val hours:String = "",
    val minutes:String = "",
    val description:String = "",

    val listProjectsControl: ProjectsControlResponseModel? = null,

    val listProjects: List<ProjectResponseModel> = emptyList(),

    val isSet: Boolean = true,

    val isVisibilityDataEntryComponent: Boolean = true,

    val listExpendedDescription: List<Boolean> = emptyList()
)
