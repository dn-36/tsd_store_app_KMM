package com.profile.profile.screens.main_refactor.screens.warehouse.viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class WarehouseIntents {
    data class AddWarehouse(val coroutineScope: CoroutineScope):WarehouseIntents()
}