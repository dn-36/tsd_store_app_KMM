package com.project.`menu-crm-api`.viewmodel

import ContragentsScreensApi
import androidx.lifecycle.ViewModel
import com.example.`notes-screens-api`.NotesScreensApi
import com.project.network.Navigation
import org.example.project.presentation.menu_feature.viewmodel.MenuIntents
import org.koin.mp.KoinPlatform.getKoin

class MenuViewModel:ViewModel() {
   // private val noteScreen: = getKoin().get()
    fun processIntent(intent: MenuIntents){
        when(intent){

            is MenuIntents.Notes -> { shownotesIntent() }

            is MenuIntents.Contragents -> { contragents() }

        }
    }

    fun shownotesIntent(){

        val notesScreen: NotesScreensApi = getKoin().get()

        Navigation.navigator.push(notesScreen.notesScreen())

    }

    fun contragents(){

        val contragentsScreen: ContragentsScreensApi = getKoin().get()

        Navigation.navigator.push(contragentsScreen.contragents())

    }

}