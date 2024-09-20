package product_network.model

import kotlinx.serialization.Serializable

@Serializable
data class Ediz(
    val id: Int?,
    val company_id: Int?,
    val name: String?,
    val ui: String?,
    val created_at: String?,
    val updated_at: String?
)