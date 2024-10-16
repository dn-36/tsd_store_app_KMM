package org.example.project.presentation.profile_feature.main_feature.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.chats.WarehouseScreensApi
import com.project.core_app.ConstData
import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import com.project.network.Navigation
import com.project.network.notes_network.NotesApi
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

       val keyValueStorage: SharedPrefsApi = getKoin().get()

        if(profileState.isUsed.value) {

            profileState.isUsed.value = false

            val token = ConstData.TOKEN

            NotesApi.token = token

            val notesApi = NotesApi



            coroutineScope.launch(Dispatchers.IO) {

               val user = notesApi.getUsers().find { it.phone == keyValueStorage.getCurrentNumber()!! }

                //println("${notesApi.getUsers()}")

                profileState = profileState.copy(
                    name = user!!.name!!,
                    numberPhone = keyValueStorage.getCurrentNumber()!!
                )

                println("${keyValueStorage.getCurrentNumber()!!}")
                //println("${keyValueStorage.getCurrentNumber("currentName")}")

            }
        }
    }
}