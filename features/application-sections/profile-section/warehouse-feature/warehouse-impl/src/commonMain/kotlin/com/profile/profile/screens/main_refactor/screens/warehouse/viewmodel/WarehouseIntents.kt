package com.profile.profile.screens.main_refactor.screens.warehouse.viewmodel

import com.project.network.warehouse_network.model.Warehouse
import kotlinx.coroutines.CoroutineScope

sealed class WarehouseIntents {
    data class OpenWindowAddWarehouse(val coroutineScope: CoroutineScope):WarehouseIntents()
    data class OpenWindowUpdateWarehouse(val coroutineScope: CoroutineScope,

                                         val warehouse : Warehouse):WarehouseIntents()
    data class SetScreen(val coroutineScope: CoroutineScope):WarehouseIntents()
    object CanselComponent: WarehouseIntents()
    object NoDelete: WarehouseIntents()
    data class OpenDeleteComponent ( val ui: String ): WarehouseIntents()
    data class DeleteWarehouse( val coroutineScope: CoroutineScope ):WarehouseIntents()
    data class CreateWarehouse(val coroutineScope: CoroutineScope,val name: String,

                               val localId: String):WarehouseIntents()
    data class UpdateWarehouse(val coroutineScope: CoroutineScope,val ui: String,val name: String,

                               val localId: String):WarehouseIntents()

    data class InputTextSearchComponent( val text: String ): WarehouseIntents()

}