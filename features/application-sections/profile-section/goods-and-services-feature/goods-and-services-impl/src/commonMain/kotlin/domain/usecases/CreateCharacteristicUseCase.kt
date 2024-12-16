package domain.usecases

import domain.repository.GoodsAndServicesClientApi

class CreateCharacteristicUseCase (

    private val client: GoodsAndServicesClientApi,

    ) {

    suspend fun execute( name: String, parametr_id: Int, product_id: Int

    ) {

        client.createCharacteristic( name, parametr_id, product_id )
    }
}