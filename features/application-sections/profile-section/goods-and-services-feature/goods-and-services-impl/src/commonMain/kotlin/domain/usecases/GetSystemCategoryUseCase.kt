package domain.usecases

import domain.repository.GoodsAndServicesClientApi
import model.ProductGoodsServicesModel
import model.SystemCategoryGoodsServicesModel

class GetSystemCategoryUseCase (

    private val client: GoodsAndServicesClientApi,

    ) {

    suspend fun execute( ): List<SystemCategoryGoodsServicesModel> {

        return client.getSystemCategory()
    }
}