package org.example.project.presentation.feature.authorization.screens.entering_number.repository

import com.module.core.AuthorizationClient
import util.onError
import util.onSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.example.project.presentation.feature.authorization.screens.entering_number.domain.AuthorizationClientAPI

class AuthorizationClientIMPL(
private val authorizationClient: AuthorizationClient
): AuthorizationClientAPI {
    override var userStatus: UserStatus = UserStatus.NEW


    override suspend fun sendNumber(
        number: String,
        scope:CoroutineScope,
        actionOnSuccess:(isRegistred:Boolean)->Unit,
        actionOnError:()->Unit
        ) {

         authorizationClient.registerCreateCode(number).onSuccess {
             userStatus = UserStatus.NEW
             println("////////////////////////////ANSWER NEW////////////////////\n" +
                     "\n" +
                     "${number}")
             println(it)
             actionOnSuccess(false)

         }
             .onError {
                 scope.launch {
           authorizationClient.loginCreateCode(number)
               .onSuccess {
                   println("////////////////////////////ANSWER REGISTERED////////////////////\n\n${number}")
                   println(it)
                   userStatus = UserStatus.REGISTERED
                   actionOnSuccess(true)
               }.onError {
                   userStatus = UserStatus.NEW
                   actionOnError()
               }
                 }
     }
    }

    override  suspend fun checkSMS(
        code: String,
        token:String,
        number:String,
        name:String,
        actionOnSuccess:()->Unit,
        actionOnError:()->Unit
    ) {
      when(userStatus){
          UserStatus.NEW ->{}
          UserStatus.REGISTERED ->{
              authorizationClient.loginVerifyCode(number,code,token).onSuccess {
              actionOnSuccess()
          }
              authorizationClient.loginVerifyCode(number,code,token).onError {
                  actionOnError()
              }
          }
          UserStatus.NEW ->{
              authorizationClient.registerVerifyCode(number,code,name,token)

              authorizationClient.loginVerifyCode(number,code,token).onSuccess {
                  actionOnSuccess()
              }
              authorizationClient.loginVerifyCode(number,code,token).onError {
                  actionOnError()
              }

          }
      }
    }
}