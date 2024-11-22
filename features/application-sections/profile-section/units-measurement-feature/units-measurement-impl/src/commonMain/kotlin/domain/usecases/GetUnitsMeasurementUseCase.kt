package domain.usecases

import domain.repository.UnitsMeasurementClientApi
import model.UnitResponseModel

class GetUnitsMeasurementUseCase (

    private val client: UnitsMeasurementClientApi,

    ) {

    suspend fun execute ( ): List<UnitResponseModel> {

        return client.getUnitsMeasurement ()

    }
}