package domain.usecases

import domain.repository.CRMClientApi
import model.ContragentResponseModel
import model.ProductModel

class GetProductsUseCase (

    private val client: CRMClientApi,

    ) {

    suspend fun execute ( ): List<ProductModel> {

        return client.getProducts ()
    }
}