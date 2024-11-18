package com.project.project_conterol.component.data_entry_project_control.viewmodel

import com.project.project_conterol.model.ProjectResponseModel
import kotlinx.datetime.LocalDate

data class DataEntryProjectControlState(

    val projects:String = "",

    val date:LocalDate? = null,

    val hour:String = "00",

    val minutes:String = "00",

    val description:String = "",

    val expendedProject: Boolean = false,

    val expendedTime: Boolean = false,

    val isVisibilityCalendar: Boolean = false,

    val isSet: Boolean = true,

    val filteredListProjects: List<ProjectResponseModel> = emptyList(),

    val selectedProject: ProjectResponseModel? = null

)
