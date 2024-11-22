package com.domain.repository

import com.model.ContragentsResponseModel
import com.model.LocationResponseModel

interface LocationsClientApi {

    suspend fun getToken(): String

    suspend fun getLocations(): List<LocationResponseModel>
    suspend fun getContragent(): List<ContragentsResponseModel>

    suspend fun deleteLocation( id:Int )

    suspend fun createLocation(  name: String?, email: String?, phone: String?,

                                 default: Int?, text: String?,

                                 telegram: String?, whatsapp: String?, wechat: String?,

                                 point: List<Double>?, adres: String, contragent_id: Int,

                                 entity_id: Int, workers: List<Int>, langs: List<Int> )

    suspend fun updateLocation(  id: Int, name: String?, email: String?, phone: String?,

                                 default: Int?, text: String?,

                                 telegram: String?, whatsapp: String?, wechat: String?,

                                 point: List<Double>?, adres: String, contragent_id: Int,

                                 entity_id: Int, workers: List<Int>, langs: List<Int> )

}