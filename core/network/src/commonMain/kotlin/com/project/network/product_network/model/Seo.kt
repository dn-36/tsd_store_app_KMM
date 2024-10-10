package product_network.model

import kotlinx.serialization.Serializable

@Serializable
data class Seo(
    val id: Int?,
    val product_id: Int?,
    val title: String?,
    val desc: String?,
    val keys: String?,
    val text: String?,
    val text_to: String?,
    val gross_text: String?,
    val created_at: String?,
    val updated_at: String?
)