package com.project.tape.component.data_entry.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import com.project.tape.model.ContragentsResponseModel
import com.project.tape.model.ProjectResponseModel

data class DataEntryTapeState (

    val name: String = "",

    val description: String = "",

    val project: String = "",

    val contragent: String = "",


    val isSet: Boolean = true,


    val listProjects: List<ProjectResponseModel> = emptyList(),

    val filteredListProjects: List<ProjectResponseModel> = emptyList(),

    val selectedProject: ProjectResponseModel? = null,

    val expendedProjects: Boolean = false,



    val listContragents: List<ContragentsResponseModel> = emptyList(),

    val filteredListContragents: List<ContragentsResponseModel> = emptyList(),

    val selectedContragent: ContragentsResponseModel? = null,

    val expendedContragents: Boolean = false,


    val image: ImageBitmap? = null,

    val video: String = ""

)
