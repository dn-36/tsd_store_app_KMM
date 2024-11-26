package com.project.tape.domain.usecases

import com.project.tape.domain.repository.TapeClientApi
import com.project.tape.model.ContragentsResponseModel

class CreatePhotoOrVideoUseCase (

    private val client: TapeClientApi,

    ) {

    suspend fun execute ( name: String,text: String, image: String,

                          format_image: String, video: String, format_video: String,

                          contragent_id: String, project_id: String

    ) {

        return client.createPhotoOrVideo ( name, text, image, format_image, video, format_video,

            contragent_id, project_id)
    }
}