package com.model

data class LocationResponseModel(

    val id: Int?,
    val adres: String?,
    val name: String?,
    val contragent: ContragentModel?,
    val default: Int?,
    val ui: String?,
    val email: String?,
    val entity: EntityModel?,
    val phone: String?,
    val text: String?,
    val workers: List<WorkerModel>?

)

data class WorkerModel(
    val id: Int?,
    val name: String?,
    val telegram: String?,
    val wechat: String?,
    val ui: String?,
    val email: String?,
    val phone: String?,
    val whatsapp: String?,
    val text: String?
)

data class EntityModel(
    val id: Int?,
    val name: String?,
    val ui: String?,
    val company: CompanyModel?,
    val own: Int?,
    val email: String?,
    val fact_address: String?,
    val kpp: String?,
    val inn: String?,
    val nds: String?,
    val ogrn: String?,
    val okpo: String?,
    val phone: String?,
    val ur_address: String?
)

data class ContragentModel(
    val id: Int?,
    val name: String?,
    val ui: String?,
    val own: Int?
)

data class CompanyModel(
    val id: Int?,
    val name: String?,
    val creater_id: Int?,
    val company_id: Int?,
    val created_at: String?,
    val updated_at: String?,
    val own: Int?
)


