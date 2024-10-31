package model

data class SpecificResponseModel (
        val id: Int,
        val company_id: Int?,
        val text: String?,
        val ui: String,
        val price: Int?,
        val status: Int?,
        val valuta_id: Int? = null,
        val created_at: String?,
        val updated_at: String,
        val local_store_id: Int? = null,
        val entity_id: Int?,
        val specification_id: Int? = null
)