package model


import kotlinx.serialization.json.JsonElement

data class ApiResponseCRMModel(
    val id: Int?,
    val service_id: Int,
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
    val active: JsonElement? = null,
    val flesh: Int?,
    val del: Int?,
    val nomer: Int?,
    val statusid: Int?,
    val data: String?,
    val date_start: String?,
    val date_stop: String?,
    val price: Double?,
    val template: Int?,
    val status_pay: String?,
    val template_value: String?,
    val task: String?,
    val created_at: String,
    val updated_at: String,
    val entity_id: Int?,
    val group_entity_id: Int?,
    val check: Int?,
    val log_check: String?,
    val check_our: Int?,
    val log_check_our: Int?,
    val our_entity_id: Int?,
    val cafe_type: Int?,
    val resource_id: Int?,
    val cafe_id: Int?,
    val invoice_pay: String?,
    val type_pay: Int?,
    val specification_id: Int?,
    val account_entity_id: Int?,
    val verify_pay: String?,
    val system: Int?,
    val search_worker: Int?,
    val no_view_client: Int?,
    val is_order_creater: Int?,
    val to_local_id: Int?,
    val from_local_id: Int?,
    val cargos: List<CargoModel>?,
    val entity_our: EntityOurModel?,
    val specs: SpecsModel?,
    val entity: EntityModel?,
    val projects: ProjectModel?,
    val groupentits: GroupEntityModel?,
    val service: ServiceModel?,
    val value:List<ValModel>?
)
data class ValModel(
    val id: Int,
    val arenda_id: Int,
    val type_id: Int,
    val `val`: ValDetailOrStringModel? = null,
    val created_at: String,
    val updated_at: String,
    val items_type: ItemsTypeModel
)

data class ValDetailOrStringModel(
    val detail: ValDetailModel? = null,
    val raw: String? = null
)

data class ValDetailModel(
    val id: Int?,
    val name: String? = null,
    val text: String? = null,
    val email: String? = null,
    val email_verified_at: String? = null,
    val phone: String? = null,
    val ui: String? = null,
    val policy: Int? = null, // Сделано необязательным
    val created_at: String? = null,
    val updated_at: String? = null,
    val tema: String? = null,
    val active: Int? = null, // Сделано необязательным
    val inn: String? = null,
    val image: String? = null,
    val contragents: Int? = null, // Сделано необязательным
    val price: Double? = null,
    val lang_id: Int? = null, // Сделано необязательным
   // val company: List<CompanyModel>? = null
)
data class ItemsTypeModel(
    val id: Int?,
    val service_id: Int?,
    val name: String?,
    val type: String?,
    val metka: String?,
    val search: Int?,
    val req: Int?,
    val created_at: String?,
    val updated_at: String?
)

data class ServiceModel(
    val id: Int?,
    val name: String?,
    val text: String?,
    val doc: String?,
    val ui: String?,
    val comp_project: String?,
    val items_value: List<ItemValueModel>?,
    /*val view: Int?,
    val view_comp_project: String?,
    val store: Int?,
    val delete_status: Int?,
    val company_id: Int?,
    val created_at: String?,
    val updated_at: String?,
    val need_check: Int?,
    val check_entity_id: Int?,
    val check_group_entity_id: Int?,
    val need_check_our: Int?,
    val limit_group_entity_id: Int?,
    val default_entity_id: Int?,
    val background: String?,
    val image: String?,
    val items_value: List<ItemValueModel>?,
    val all_check_users_id: List<Int>?,
    val all_check_users_other: List<String>?*/
)

data class ItemValueModel(
    val id: Int?,
    val service_id: Int?,
    val name: String?,
    val type: String?,
    val metka: String?,
    val search: Int?,
    val req: Int?,
    val created_at: String?,
    val updated_at: String?
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

data class EntityOurModel(
    val id: Int?,
    val own: Int?,
    //val creater_id: Int?,
    val contragent_id: Int?,
    val company_id: Int?,
    val name: String?,
    /*val inn: String?,
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
    val user_id: Int?,
    val company: CompanyModel?*/
)

data class SpecsModel(
    val id: Int?,
    val company_id: Int?,
    val text: String?
)

data class EntityModel(
    val id: Int?,
    val own: Int?,
    val creater_id: Int?,
    val name: String?
)

data class Active(
    val id: Int,
    val service_id: Int
)

data class ProjectModel(
    val id: Int,
    val name: String
)

data class GroupEntityModel(
    val id: Int,
    val name: String
)

data class CargoModel(
    val id: Int,
    val company_id: Int,
    val name: String,
    val ui: String,

)