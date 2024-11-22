package domain.usecases

import domain.repository.UnitsMeasurementClientApi
import model.UnitResponseModel

class CreateUnitsMeasurementUseCase (

    private val client: UnitsMeasurementClientApi,

    ) {

    suspend fun execute ( name: String ) {

        return client.createUnitMeasurement ( name )

    }
}