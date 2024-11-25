package com.project.tape.domain.usecases

import com.project.tape.domain.repository.TapeClientApi
import com.project.tape.model.TapeResponseModel

class GetVideoUseCase (

    private val client: TapeClientApi,

    ) {

    suspend fun execute ( ): List<TapeResponseModel> {

        return client.getVideo ( )
    }
}