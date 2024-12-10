package domain.usecases

import domain.repository.GoodsAndServicesClientApi
import model.ProductGoodsServicesModel

class DeleteGoodOrServiceUseCase (

    private val client: GoodsAndServicesClientApi,

    ) {

    suspend fun execute( id: Int ) {

        client.deleteGoodOrService( id )
    }
}