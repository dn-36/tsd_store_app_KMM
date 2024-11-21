package com.project.project_conterol.model

data class ProjectsControlResponseModel(

    val data: List<ServiceModel>?,
    val balans: Int?

)

data class ServiceModel(
    val id: Int?,
    val user_id: Int?,
    val project_id: Int?,
    val text: String?,
    val price: Int?,
    val data: String?,
    val time: String?,
    val created_at: String?,
    val updated_at: String?,
    val company_id: Int?,
    val type: String?,
    val project: ProjectModel?,
    val user: UserModel?
)

data class ProjectModel(
    val id: Int?,
    val name: String?,
    /* val creater_id: Int,
     val company_id: Int,
     val created_at: String,
     val updated_at: String,
     val entity_id: Int?,
     val active: Int,
     val entity_client_id: Int?,
     val entity: EntityModel?*/
)

data class EntityModel(
    val id: Int?,
    val own: Int?,
    val creater_id: Int?,
    val contragent_id: Int?,
    val company_id: Int?,
    val name: String?,
    val inn: String?,
    val kpp: String?,
    val okpo: String?,
    val ogrn: String?,
    val base: String?,
    val ur_address: String?,
    val fact_address: String?,
    val fio: String?,
    val phone: String?,
    val email: String?,
    val nds: String?,
    val print: String?,
    val sign: String?,
    val book_sign: String?,
    val ui: String?,
    val file_verify: String?,
    val verify: Int?,
    val created_at: String?,
    val updated_at: String?,
    val user_id: Int?
)

data class UserModel(
    val id: Int?,
    val name: String?,
    /*val email: String?,
    val email_verified_at: String?,
    val phone: String,
    val ui: String,
    val policy: Int,
    val created_at: String,
    val updated_at: String,
    val tema: String,
    val active: Int,
    val inn: String?,
    val image: String?,
    val contragents: Int,
    val price: Int,
    val lang_id: Int*/
)

