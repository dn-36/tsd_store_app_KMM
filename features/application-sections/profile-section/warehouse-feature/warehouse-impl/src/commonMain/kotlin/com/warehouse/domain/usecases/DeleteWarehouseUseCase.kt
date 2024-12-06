package com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases

import com.profile.profile.screens.main_refactor.screens.warehouse.domain.repository.WarehouseClientApi

class DeleteWarehouseUseCase (

    private val client: WarehouseClientApi,

    ) {

    suspend fun execute( ui:String,
                         onDelete: () -> Unit

    ){

        client.deleteWarehouse (
            ui, onDelete
        )
    }
}