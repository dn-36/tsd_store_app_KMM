package product_network.model

import kotlinx.serialization.Serializable

@Serializable
data class Local(
    val id: Int?,
    val name: String?,
    val ui: String?,
    val company_id: Int?,
    val local_id: Int?,
    val created_at: String?,
    val updated_at: String?,
    val default: Int?
)