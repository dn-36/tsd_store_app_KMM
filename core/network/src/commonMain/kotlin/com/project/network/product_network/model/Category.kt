package product_network.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int?,
    val name: String?,
    val creater_id: Int?,
    val company_id: Int?,
    val created_at: String?,
    val updated_at: String?
)