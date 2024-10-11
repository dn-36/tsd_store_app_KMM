package com.project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.core_app.ConstData
import com.project.network.organizations_network.OrganizationsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class OrganizationsViewModel:ViewModel() {

    var organizationsState by mutableStateOf(OrganizationsState())

    fun processIntent(intent: OrganizationsIntents){
        when(intent){
            is OrganizationsIntents.SetScreen -> setScreen(intent.coroutineScope)
        }
    }

    fun setScreen(coroutineScope: CoroutineScope){

        if(organizationsState.iUsed.value) {

            organizationsState.iUsed.value = false

            val token = ConstData.TOKEN

            OrganizationsApi.token = token

            coroutineScope.launch(Dispatchers.IO) {

                val allOrganizations = OrganizationsApi.getOrganizations()

                println(
                    "-------${allOrganizations}--------------"
                )
                organizationsState = organizationsState.copy(
                    allOrganizations = allOrganizations
                )

            }
        }
    }
}