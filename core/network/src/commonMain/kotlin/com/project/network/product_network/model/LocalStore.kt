package product_network.model

import kotlinx.serialization.Serializable
import product_network.ProductApiClient


@Serializable
data class LocalStore(
    val id: Int?,
    val store_id: Int?,
    val product_id: Int?,
    @Serializable(with = ProductApiClient.CountSerializer::class) val stock: Double?,
    val reserve: Int?,
    val order: Int?,
    val created_at: String?,
    val updated_at: String?,
    val local: Local?
)