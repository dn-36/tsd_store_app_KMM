package domain.usecases

import domain.repository.GoodsAndServicesClientApi

class UpdateCharacteristicUseCase (

    private val client: GoodsAndServicesClientApi,

    ) {

    suspend fun execute( name: String, parametr_id: Int, product_id: Int, id:Int

    ) {

        client.updateCharacteristic( name, parametr_id, product_id, id )
    }
}