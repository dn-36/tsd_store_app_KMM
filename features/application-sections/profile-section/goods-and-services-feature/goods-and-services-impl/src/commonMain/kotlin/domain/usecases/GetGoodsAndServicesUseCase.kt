package domain.usecases

import androidx.compose.ui.graphics.Color
import com.project.network.organizations_network.model.Response
import domain.repository.GoodsAndServicesClientApi
import model.ProductGoodsServicesModel

class GetGoodsAndServicesUseCase (

    private val client: GoodsAndServicesClientApi,

    ) {

    suspend fun execute( ): List<ProductGoodsServicesModel> {

         return client.getGoodsAndServices()
    }
}