package product_network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val current_page: Int?,
    val data: List<Product>?
)