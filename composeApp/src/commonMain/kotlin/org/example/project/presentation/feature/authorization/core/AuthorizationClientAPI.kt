package org.example.project.presentation.feature.authorization.core

import kotlinx.coroutines.CoroutineScope
import org.example.project.presentation.feature.authorization.core.repository_impl.authorization_client.UserStatus

interface AuthorizationClientAPI {
    companion object{
        var userStatus: UserStatus = UserStatus.NEW
    }

    suspend fun sendNumber(
                   number:String,
                   scope:CoroutineScope,
                   actionOnSuccess:(Boolean)->Unit,
                   actionOnError:()->Unit
    )

    suspend fun checkSMS(
        code: String,
        token:String,
        number:String,
        name:String,
        actionOnSuccess:()->Unit,
        actionOnError:()->Unit
    )
}