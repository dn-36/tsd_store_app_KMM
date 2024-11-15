package com.viewmodel

import com.model.LocationResponseModel
import kotlinx.coroutines.CoroutineScope

sealed class LocationsIntents {

    object Back:LocationsIntents()

    object BackFromDataEntry:LocationsIntents()

    data class SetScreen( val coroutineScope: CoroutineScope ): LocationsIntents()

    data class OpenDeleteComponent ( val item: LocationResponseModel ): LocationsIntents()

    data class OpenCreateDataEntryComponent (val coroutineScope: CoroutineScope ): LocationsIntents()

    object NoDelete: LocationsIntents()

    data class DeleteLocation( val coroutineScope: CoroutineScope ): LocationsIntents()

}