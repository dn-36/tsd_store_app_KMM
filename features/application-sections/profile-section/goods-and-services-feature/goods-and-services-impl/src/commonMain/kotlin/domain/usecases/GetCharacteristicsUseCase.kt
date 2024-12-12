package domain.usecases

import domain.repository.GoodsAndServicesClientApi
import model.CategoryGoodsServicesModel
import model.CharacteristicModel

class GetCharacteristicsUseCase (

    private val client: GoodsAndServicesClientApi,

    ) {

    suspend fun execute( ): List<CharacteristicModel> {

        return client.getCharacteristics()
    }
}