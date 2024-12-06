package domain.usecases

import domain.repository.GoodsAndServicesClientApi
import model.CategoryGoodsServicesModel
import model.ProductGoodsServicesModel

class GetCategoryUseCase (

    private val client: GoodsAndServicesClientApi,

    ) {

    suspend fun execute( ): List<CategoryGoodsServicesModel> {

        return client.getCategory()
    }
}