package org.example.project.presentation.profile_feature.main_feature.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import com.profile.profile.screens.warehouse_feature.main_refactor.screen.WarehouseScreen
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

        Navigation.navigator.push(WarehouseScreen)

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