package com.project.tape.domain.repository

import com.project.tape.model.ContragentsResponseModel
import com.project.tape.model.ProjectResponseModel
import com.project.tape.model.TapeResponseModel

interface TapeClientApi {

    suspend fun getToken(): String

    suspend fun getVideo(): List<TapeResponseModel>

    suspend fun getProjects(): List<ProjectResponseModel>

    suspend fun getContragents(): List<ContragentsResponseModel>

    suspend fun createPhotoOrVideo ( name: String,text: String, image: String?,

                                     format_image: String, video: String?, format_video: String )

}