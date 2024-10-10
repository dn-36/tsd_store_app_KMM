package org.example.project.presentation.profile_feature.main_feature.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.chats.WarehouseScreensApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import com.project.network.Navigation
import com.project.`local-storage`.`profile-storage`.KeyValueStorageApi
import org.koin.mp.KoinPlatform.getKoin

class ProfileViewModel:ViewModel() {

    var profileState by mutableStateOf(ProfileState())

    fun processIntent(intents: ProfileIntents){

        when(intents) {

            is ProfileIntents.Warehouse -> { warehouse() }

            is ProfileIntents.SetScreen -> { setScreen(intents.coroutineScope) }

        }
    }

    fun warehouse(){

        val warehouseScreen: WarehouseScreensApi = getKoin().get()

        Navigation.navigator.push(warehouseScreen.warehouse())

    }

    fun setScreen(coroutineScope: CoroutineScope){

       val keyValueStorage: KeyValueStorageApi = getKoin().get()

        if(profileState.isUsed.value) {

            profileState.isUsed.value = false

         //   val token = ConstData.TOKEN

           // NotesApi.token = token

            coroutineScope.launch(Dispatchers.IO) {

                //println("${notesApi.getUsers()}")

                keyValueStorage.saveCurrentNumber("currentNumber","+79963799050")

                keyValueStorage.saveCurrentName("currentName","DIMA")

                profileState = profileState.copy(
                    name = keyValueStorage.getCurrentNumber("currentName")!!,
                    numberPhone = keyValueStorage.getCurrentNumber("currentNumber")!!
                )

                println("${keyValueStorage.getCurrentNumber("currentNumber")}")
                println("${keyValueStorage.getCurrentNumber("currentName")}")

            }
        }
    }
}