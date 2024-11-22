package domain.repository

import model.UnitResponseModel

interface UnitsMeasurementClientApi {

    suspend fun getToken(): String

    suspend fun getUnitsMeasurement(): List<UnitResponseModel>

    suspend fun createUnitMeasurement( name: String )

    suspend fun updateUnitMeasurement( name: String, ui: String )

    suspend fun deleteUnitMeasurement( ui: String )

}