package product_network.model

import com.project.network.product_network.model.Product
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val current_page: Int?,
    val data: List<Product>?
)