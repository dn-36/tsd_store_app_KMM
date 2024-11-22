package com.project.menu_crm_api.viewmodel

import CRMScreenApi import androidx.lifecycle.ViewModel
import com.project.network.Navigation
import org.koin.mp.KoinPlatform.getKoin

class MenuViewModel:ViewModel() {
   // private val noteScreen: = getKoin().get()
    fun processIntent(intent: MenuIntents){

        when(intent){

            is MenuIntents.CRM ->  crm()

        }
    }

    fun crm () {

        val crmScreen: CRMScreenApi = getKoin().get()

        Navigation.navigator.push(crmScreen.crm())

    }

}