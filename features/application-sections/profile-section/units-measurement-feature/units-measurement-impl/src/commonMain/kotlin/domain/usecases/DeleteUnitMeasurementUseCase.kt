package domain.usecases

import domain.repository.UnitsMeasurementClientApi

class DeleteUnitMeasurementUseCase (

    private val client: UnitsMeasurementClientApi,

    ) {

    suspend fun execute ( ui: String ) {

        return client.deleteUnitMeasurement ( ui )

    }
}