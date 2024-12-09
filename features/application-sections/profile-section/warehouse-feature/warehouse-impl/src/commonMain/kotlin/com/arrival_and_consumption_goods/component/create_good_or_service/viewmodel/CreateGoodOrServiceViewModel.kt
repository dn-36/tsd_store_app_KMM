package com.arrival_and_consumption_goods.component.create_good_or_service.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.arrival_and_consumption_goods.domain.usecases.GetCategoriesUseCase
import com.project.core_app.network_base_screen.NetworkViewModel

class CreateGoodOrServiceViewModel(): ViewModel() {

    var state by mutableStateOf(CreateGoodOrServiceState())

    fun processIntents( intent: CreateGoodOrServiceIntents ) {

        //when ( intent ) {

          //  is C

       // }

    }


}