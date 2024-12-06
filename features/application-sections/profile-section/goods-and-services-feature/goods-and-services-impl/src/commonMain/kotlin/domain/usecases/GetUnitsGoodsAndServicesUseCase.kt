package domain.usecases

import domain.repository.GoodsAndServicesClientApi
import model.UnitGoodsAndServicesModel

class GetUnitsGoodsAndServicesUseCase (

    private val client: GoodsAndServicesClientApi,

    ) {

    suspend fun execute( ): List<UnitGoodsAndServicesModel> {

        return client.getUnitsMeasurement()
    }
}