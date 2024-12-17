package domain.usecases

import domain.repository.GoodsAndServicesClientApi

class DeleteCharacteristicUseCase (

    private val client: GoodsAndServicesClientApi,

    ) {

    suspend fun execute( id: Int ) {

        client.deleteCharacteristic( id )
    }
}