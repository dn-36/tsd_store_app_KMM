package org.example.project.presentation.feature.authorization.screens.entering_number.domain

import kotlinx.coroutines.CoroutineScope
import org.example.project.presentation.feature.authorization.core.AuthorizationClientAPI

class SendNumberUseCase (private val client: AuthorizationClientAPI) {
    suspend fun excecute(number:String,
                 scope: CoroutineScope,
                 actionOnSuccess:(status:Boolean)->Unit,
                 actionOnError:()->Unit){
        client.sendNumber(
            number,
            scope,
            actionOnSuccess,
            actionOnError
        )
    }
}