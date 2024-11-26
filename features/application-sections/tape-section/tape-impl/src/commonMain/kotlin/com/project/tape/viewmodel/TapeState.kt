package com.project.tape.viewmodel

import com.project.tape.model.ContragentsResponseModel
import com.project.tape.model.ProjectResponseModel
import com.project.tape.model.TapeResponseModel

data class TapeState(

    val listVideo: List<TapeResponseModel> = emptyList(),

    val listProjects: List<ProjectResponseModel> = emptyList(),

    val listContragents: List<ContragentsResponseModel> = emptyList(),

    val isUsed: Boolean = true,

    val isVisibilityDataEntry: Boolean = false

)
