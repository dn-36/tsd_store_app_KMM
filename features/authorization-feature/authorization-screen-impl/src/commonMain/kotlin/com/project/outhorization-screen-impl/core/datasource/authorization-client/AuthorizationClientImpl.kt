package com.project.`outhorization-screen-impl`.core.datasource.`authorization-client`

import com.project.network.authorization_network.AuthorizationClient
import com.project.`outhorization-screen-impl`.screens.check_sms.domain.repository.AuthorizationClientAPI
import com.project.`outhorization-screen-impl`.screens.check_sms.domain.repository.AuthorizationClientAPI.Companion.userStatus
import util.onError
import util.onSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.project.`outhorization-screen-api`.UserStatus

class AuthorizationClientImpl(
private val authorizationClient: AuthorizationClient
): AuthorizationClientAPI {

    override suspend fun sendNumber(
        number: String,
        scope:CoroutineScope,
        actionOnSuccess:(isRegistred:Boolean)->Unit,
        actionOnError:()->Unit
        ) {

         authorizationClient.registerCreateCode(number).onSuccess {
             userStatus = UserStatus.NEW
             println("/////////////////ANSWER NEW////////////////////\n" +
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

    override suspend fun checkSMS(
        code: String,
        token: String,
        number: String,
        name: String,
        actionOnSuccess: suspend () -> Unit,
        actionOnError: () -> Unit
    ) {



        when(userStatus){
          UserStatus.REGISTERED ->{
             //
              authorizationClient.loginVerifyCode(number,code,token).onSuccess {
              actionOnSuccess()
          }
              authorizationClient.loginVerifyCode(number,code,token).onError {
                  actionOnError()
              }
          }
          UserStatus.NEW ->{

              authorizationClient.registerVerifyCode(number,code,name,token).onSuccess {
                  actionOnSuccess()
              }
              authorizationClient.registerVerifyCode(number,code,name,token).onError {
                  actionOnError()
              }

          }
      }
    }
}