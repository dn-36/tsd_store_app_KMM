package com.project.`menu-crm-api`.viewmodel

import CRMScreenApi
import ContragentsScreensApi
import androidx.lifecycle.ViewModel
import com.SpecificationsScreenApi
import com.example.`notes-screens-api`.NotesScreensApi
import com.project.`menu-crm-api`.ProjectControlScreenApi
import com.project.network.Navigation
import org.example.project.presentation.menu_feature.viewmodel.MenuIntents
import org.koin.mp.KoinPlatform.getKoin

class MenuViewModel:ViewModel() {
   // private val noteScreen: = getKoin().get()
    fun processIntent(intent: MenuIntents){
        when(intent){

            is MenuIntents.Notes ->  shownotesIntent()

            is MenuIntents.Contragents ->  contragents()

            is MenuIntents.CRM ->  crm()

            is MenuIntents.ProjectControl ->  projectControl()

            is MenuIntents.Specifications ->  specifications()

        }
    }

    fun shownotesIntent () {

        val notesScreen: NotesScreensApi = getKoin().get()

        Navigation.navigator.push(notesScreen.notesScreen())

    }

    fun contragents () {

        val contragentsScreen: ContragentsScreensApi = getKoin().get()

        Navigation.navigator.push(contragentsScreen.contragents())

    }

    fun crm () {

        val crmScreen: CRMScreenApi = getKoin().get()

        Navigation.navigator.push(crmScreen.crm())

    }

    fun projectControl () {

        val projectControlScreen: ProjectControlScreenApi = getKoin().get()

        Navigation.navigator.push(projectControlScreen.projectControl())

    }

    fun specifications () {

        val specificationsScreen: SpecificationsScreenApi = getKoin().get()

        Navigation.navigator.push(specificationsScreen.specifications())

    }

}