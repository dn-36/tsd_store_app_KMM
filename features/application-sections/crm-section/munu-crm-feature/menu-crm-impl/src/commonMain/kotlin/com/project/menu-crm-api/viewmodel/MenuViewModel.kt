package com.project.`menu-crm-api`.viewmodel

import androidx.lifecycle.ViewModel
import org.example.project.presentation.menu_feature.viewmodel.MenuIntents

class MenuViewModel:ViewModel() {
   // private val noteScreen: = getKoin().get()
    fun processIntent(intent: MenuIntents){
        when(intent){

            is MenuIntents.ClickedBookmarks -> {clickBookmarksIntent()}

        }
    }

    fun clickBookmarksIntent(){
       // Navigation.navigator.push(NotesScreen)

    }

}