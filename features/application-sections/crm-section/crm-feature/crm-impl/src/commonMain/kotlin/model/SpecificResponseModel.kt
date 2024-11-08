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
        val specification_id: Int? = null,
        val spec_item: List<SpecItemModel>?
)
data class SpecItemModel (

        val id: Int,
        val spec_id: Int?,
        val product_id: Int?,
        val price_id: Int? = null,
        val block: String?,
        val count: Int?,
        val price: Double?,
        val nds: Int? = null,
        val text: String? = null,
        val created_at: String?,
        val updated_at: String?,
        val cafe_send: Int?,
        val spec_item_id: Int? = null,
        val numb: Int? = null,
        val work: Int,
        val sale: Int? = null,
       // val product: Product?
)