package domain.usecases

import domain.repository.GoodsAndServicesClientApi
import model.ProductGoodsServicesModel

class GetSpecificGoodOrServiceUseCase (

    private val client: GoodsAndServicesClientApi,

    ) {

    suspend fun execute( ui: String ): ProductGoodsServicesModel {

        return client.getSpecificGoodOrService( ui )
    }
}