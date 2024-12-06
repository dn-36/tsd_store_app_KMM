package com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases

import com.profile.profile.screens.main_refactor.screens.warehouse.domain.repository.WarehouseClientApi

class UpdateWarehouseUseCase (

    private val client: WarehouseClientApi,

    ) {

    suspend fun execute( ui:String,
                         name:String,
                         localId:String,
                         onUpdate: () -> Unit

    ){

        client.updateWarehouse (
            ui, name, localId, onUpdate
        )
    }
}