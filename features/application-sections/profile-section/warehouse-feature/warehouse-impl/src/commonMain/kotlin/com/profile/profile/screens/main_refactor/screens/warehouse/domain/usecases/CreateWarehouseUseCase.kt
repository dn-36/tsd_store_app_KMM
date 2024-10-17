package com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases

import com.profile.profile.screens.main_refactor.screens.warehouse.domain.repository.WarehouseClientApi

class CreateWarehouseUseCase (

    private val client: WarehouseClientApi,

    ) {

    suspend fun execute( name:String,
                         localId:String,
                         onCreate: () -> Unit

    ){

        client.createWarehouse (
            name, localId, onCreate
        )
    }
}