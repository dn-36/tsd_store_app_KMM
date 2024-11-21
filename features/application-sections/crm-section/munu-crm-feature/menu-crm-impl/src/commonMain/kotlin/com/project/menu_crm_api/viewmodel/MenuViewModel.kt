package com.project.menu_crm_api.viewmodel

import CRMScreenApi
import ContragentsScreensApi
import androidx.lifecycle.ViewModel
import com.LocationsScreenApi
import com.SpecificationsScreenApi
import com.example.`notes-screens-api`.NotesScreensApi
import com.project.network.Navigation
import org.koin.mp.KoinPlatform.getKoin

class MenuViewModel:ViewModel() {
   // private val noteScreen: = getKoin().get()
    fun processIntent(intent: MenuIntents){
        when(intent){

            is MenuIntents.Notes ->  shownotesIntent()

            is MenuIntents.Contragents ->  contragents()

            is MenuIntents.CRM ->  crm()

            is MenuIntents.Specifications ->  specifications()

            is MenuIntents.Locations ->  locations()

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

    fun specifications () {

        val specificationsScreen: SpecificationsScreenApi = getKoin().get()

        Navigation.navigator.push(specificationsScreen.specifications())

    }

    fun locations () {

       val locationsScreen: LocationsScreenApi = getKoin().get()

        Navigation.navigator.push(locationsScreen.locations())

    }

}