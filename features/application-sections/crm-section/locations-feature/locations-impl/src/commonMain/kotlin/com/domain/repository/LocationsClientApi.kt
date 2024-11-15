package com.domain.repository

import com.model.LocationResponseModel

interface LocationsClientApi {

    suspend fun getToken(): String

    suspend fun getLocations(): List<LocationResponseModel>

    suspend fun deleteLocation( id:Int )

}