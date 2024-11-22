package domain.usecases

import domain.repository.UnitsMeasurementClientApi

class UpdateUnitsMeasurementUseCase (

    private val client: UnitsMeasurementClientApi,

    ) {

    suspend fun execute ( name: String, ui: String ) {

        return client.updateUnitMeasurement ( name, ui )

    }
}