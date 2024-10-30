package model

import kotlinx.serialization.Serializable

data class ApiResponseCRMModel(
    val id: Int?,
    val service_id: Int?,
    val company_id: Int?,
    val project_id: Int?,
    val organization_id: Int?,
    val user_id: Int?,
    val active_company_id: Int?,
    val company_work: String?,
    val arenda_id: Int?,
    val status: String?,
    val text: String?,
    val ui: String?,
    val active: ActiveModel?,
    val flesh: Int?,
    val del: Int?,
    val nomer: Int?,
    val statusid: Int?,
    val data: String?,  // Если "data" содержит сложные данные, можно указать точный тип
    val date_start: String?,  // Можно использовать `LocalDate` если нужна дата
    val date_stop: String?,
    val price: Int?,
    val template: Int?,
    val status_text: String? = ""

)

@Serializable
data class ActiveModel(
    val id: Int,
    val service_id: Int,
)
