package org.example.project.presentation.profile_feature.main_feature.viewmodel

import ContragentsScreensApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.SpecificationsScreenApi
import com.example.`notes-screens-api`.NotesScreensApi
import com.project.chats.WarehouseScreensApi
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.`menu-crm-api`.ProjectControlScreenApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import com.project.network.Navigation
import com.project.network.users_network.UsersClient
import com.project.`printer-api`.PrinterScreensApi
import org.koin.mp.KoinPlatform.getKoin

class ProfileViewModel : NetworkViewModel() {

    var profileState by mutableStateOf(ProfileState())

    fun processIntent(intents: ProfileIntents){

        when(intents) {

            is ProfileIntents.Warehouse -> warehouse()

            is ProfileIntents.Projects -> projects()

            is ProfileIntents.Contragents -> contragents()

            is ProfileIntents.Specifications -> specifications()

            is ProfileIntents.Notes -> notes()

            is ProfileIntents.SetScreen -> setScreen(intents.coroutineScope)

            ProfileIntents.Tools -> {tools()}
        }
    }
    fun tools(){

        val toolsScreen: PrinterScreensApi = getKoin().get()

        Navigation.navigator.push(toolsScreen.printer())//toolsScreen.tools())

    }

    fun warehouse(){

        val warehouseScreen: WarehouseScreensApi = getKoin().get()

        Navigation.navigator.push(warehouseScreen.warehouse())

    }

    fun notes(){

        val notesScreen: NotesScreensApi = getKoin().get()

        Navigation.navigator.push(notesScreen.notesScreen())

    }

    fun projects(){

        val projectsScreen: ProjectControlScreenApi = getKoin().get()

        Navigation.navigator.push(projectsScreen.projectControl())

    }

    fun contragents(){

        val contragentsScreen: ContragentsScreensApi = getKoin().get()

        Navigation.navigator.push(contragentsScreen.contragents())

    }

    fun specifications(){

        val specificationsScreen: SpecificationsScreenApi = getKoin().get()

        Navigation.navigator.push(specificationsScreen.specifications())

    }

    fun setScreen(scope: CoroutineScope){

       val keyValueStorage: SharedPrefsApi = getKoin().get()

        if(profileState.isUsed.value) {

            profileState.isUsed.value = false

            scope.launch(Dispatchers.IO) {

            val usersApi = UsersClient().init(keyValueStorage.getToken())

               val user = usersApi.getUsers().find { it.phone == keyValueStorage.getCurrentNumber()!! }

                //println("${notesApi.getUsers()}")

                profileState = profileState.copy(

                    name = user?.name?:"",

                    numberPhone = keyValueStorage.getCurrentNumber()!!

                )

                println("${keyValueStorage.getCurrentNumber()!!}")
                //println("${keyValueStorage.getCurrentNumber("currentName")}")

                setStatusNetworkScreen ( StatusNetworkScreen.SECCUESS )

            }
        }
    }
}