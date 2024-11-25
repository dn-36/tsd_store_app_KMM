package com.project.tape.component.data_entry.viewmodel

import com.project.tape.model.ProjectResponseModel

data class DataEntryTapeState (

    val name: String = "",

    val description: String = "",


    val isSet: Boolean = true,


    val listProjects: List<ProjectResponseModel> = emptyList()

)
