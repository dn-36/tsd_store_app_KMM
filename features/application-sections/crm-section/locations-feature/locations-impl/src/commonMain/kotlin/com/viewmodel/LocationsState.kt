package com.viewmodel

import com.model.LocationResponseModel

data class LocationsState(

    val isVisibilityDataEntry: Boolean = false,

    val isVisibilityDeleteComponent: Boolean = false,

    val listLocations: List<LocationResponseModel> = emptyList(),

    val isSet: Boolean = true,

    val updateLocation: LocationResponseModel? = null

)
