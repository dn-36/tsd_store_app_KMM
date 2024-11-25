package datasource

import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.cargo_network.CargoClient
import com.project.network.contragent_network.ContragentClient
import com.project.network.crm_network.CRMClient
import com.project.network.crm_network.model.ServiceItem
import com.project.network.group_entity_network.GroupEntityClient
import com.project.network.locations_network.LocationsClient
import com.project.network.projects_network.ProjectClient
import com.project.network.services_network.ServicesClient
import com.project.network.specifications_network.SpecificationsClient
import com.project.network.users_network.UsersClient
import domain.repository.CRMClientApi
import model.ApiResponseCRMModel
import model.CargoModel
import model.CargoResponseModel
import model.ContragentLocationModel
import model.ContragentResponseModel
import model.EntityContragentModel
import model.EntityModel
import model.EntityOurModel
import model.GroupEntityModel
import model.GroupEntityResponseModel
import model.ItemValueModel
import model.ItemsTypeModel
import model.LocationResponseModel
import model.ProductModel
import model.ProjectModel
import model.ProjectResponseModel
import model.ServiceItemModel
import model.ServiceResponseModel
import model.ServiceItemCreateCRMModel
import model.ServiceModel
import model.SpecItemModel
import model.SpecificResponseModel
import model.SpecsModel
import model.UserCRMModel
import model.ValDetailModel
import model.ValDetailOrStringModel
import model.ValModel
import product_network.ProductApiClient

class CRMClientImpl(

    private val crmClient: CRMClient,

    private val sharedPrefsApi: SharedPrefsApi,

    private val specificationsClient: SpecificationsClient,

    private val servicesClient: ServicesClient,

    private val usersClient: UsersClient,

    private val contragentsClient: ContragentClient,

    private val locationsClient: LocationsClient,

    private val projectsClient: ProjectClient,

    private val cargoClient: CargoClient,

    private val groupEntityClient: GroupEntityClient,

    // private val productsClient: ProductApiClient

) : CRMClientApi {

    override suspend fun getIncomingCRM(): List<ApiResponseCRMModel> {

        crmClient.init(sharedPrefsApi.getToken() ?: "")

        val newList = crmClient.getIncomingCRM().map {

            ApiResponseCRMModel(
                id = it.id,
                service_id = it.service_id,
                company_id = it.company_id,
                project_id = it.project_id,
                organization_id = it.organization_id,
                cargos = it.cargos?.map {

                    CargoModel(

                        id = it.id,
                        company_id = it.company_id,
                        name = it.name,
                        ui = it.ui

                    )
                },
                user_id = it.user_id,
                active_company_id = it.active_company_id,
                company_work = it.company_work ?: "",
                arenda_id = it.arenda_id,
                status = it.status ?: "",
                text = it.text ?: "",
                ui = it.ui ?: "",
                active = it.active,
                flesh = it.flesh,
                del = it.del,
                nomer = it.nomer,
                statusid = it.statusid,
                data = it.data ?: "",
                date_start = it.date_start ?: "",
                date_stop = it.date_stop ?: "",
                price = it.price,
                template = it.template,
                status_pay = it.status_pay ?: "",
                template_value = it.template_value ?: "",
                task = it.task ?: "",
                created_at = it.created_at,
                updated_at = it.updated_at,
                entity_id = it.entity_id,
                group_entity_id = it.group_entity_id,
                check = it.check,
                log_check = it.log_check ?: "",
                check_our = it.check_our,
                log_check_our = it.log_check_our ?: 0,
                our_entity_id = it.our_entity_id,
                cafe_type = it.cafe_type,
                resource_id = it.resource_id,
                cafe_id = it.cafe_id,
                invoice_pay = it.invoice_pay ?: "",
                type_pay = it.type_pay,
                specification_id = it.specification_id,
                account_entity_id = it.account_entity_id,
                verify_pay = it.verify_pay ?: "",
                system = it.system,
                search_worker = it.search_worker,
                no_view_client = it.no_view_client,
                is_order_creater = it.is_order_creater,
                to_local_id = it.to_local_id,
                from_local_id = it.from_local_id,
                entity_our = if (it.entity_our != null) EntityOurModel(
                    id = it.entity_our!!.id,
                    own = it.entity_our!!.own,
                    contragent_id = it.entity_our!!.contragent_id,
                    company_id = it.entity_our!!.company_id, name = it.entity_our!!.name
                ) else null,
                specs = if (it.specs != null) SpecsModel(
                    id = it.specs!!.id,

                    company_id = it.specs!!.company_id, text = it.specs!!.text
                ) else null,

                entity = if (it.entity != null) EntityModel(
                    id = it.entity!!.id,
                    own = it.entity!!.own, creater_id = it.entity!!.creater_id,
                    name = it.entity!!.name
                ) else null,

                projects = if (it.projects != null) ProjectModel(
                    id = it.projects!!.id,
                    name = it.projects!!.name
                ) else null,

                groupentits = if (it.groupentits != null) GroupEntityModel(

                    id = it.groupentits!!.id,
                    name = it.groupentits!!.name

                ) else null,

                service = if (it.service != null) ServiceModel(

                    id = it.service!!.id,
                    name = it.service!!.name,
                    text = it.service!!.text,
                    doc = it.service!!.doc,
                    ui = it.service!!.ui,
                    comp_project = it.service!!.comp_project,
                    items_value = if ( it.service!!.items_value != null ) it.service!!.items_value!!.map {

                        ItemValueModel(

                            id = it.id,
                            service_id = it.service_id,
                            name = it.name,
                            type = it.type,
                            metka = it.metka,
                            search = it.search,
                            req = it.req,
                            created_at = it.created_at,
                            updated_at = it.updated_at

                        )

                    } else null,


                ) else null,

                value = it.`val`?.map { item ->
                    ValModel(
                        id = item.id,
                        arenda_id = item.arenda_id,
                        type_id = item.type_id,
                        `val` = ValDetailOrStringModel(

                            detail = ValDetailModel(
                                id = item.`val`?.detail?.id ?: 0,
                                name = item.`val`?.detail?.name,
                                text = item.`val`?.detail?.text,
                                email = item.`val`?.detail?.email,
                                email_verified_at = item.`val`?.detail?.email_verified_at,
                                phone = item.`val`?.detail?.phone,
                                ui = item.`val`?.detail?.ui,
                                policy = item.`val`?.detail?.policy,
                                created_at = item.`val`?.detail?.created_at,
                                updated_at = item.`val`?.detail?.updated_at,
                                tema = item.`val`?.detail?.tema,
                                active = item.`val`?.detail?.active,
                                inn = item.`val`?.detail?.inn,
                                image = item.`val`?.detail?.image,
                                contragents = item.`val`?.detail?.contragents,
                                price = item.`val`?.detail?.price,
                                lang_id = item.`val`?.detail?.lang_id


                            ),
                            raw = item.`val`?.raw

                        ),
                        created_at = item.created_at,
                        updated_at = item.updated_at,
                        items_type = ItemsTypeModel(
                            id = item.items_type?.id ?: 0, // Если null, подставляется 0
                            service_id = item.items_type?.service_id ?: 0,
                            name = item.items_type?.name ?: "",
                            type = item.items_type?.type ?: "",
                            metka = item.items_type?.metka ?: "",
                            search = item.items_type?.search ?: 0,
                            req = item.items_type?.req ?: 0,
                            created_at = item.items_type?.created_at ?: "",
                            updated_at = item.items_type?.updated_at ?: ""
                        )
                    )
                }
                /*projects = it.projects,
                service = it.service,
                company = it.company,
                companactiv = it.companactiv ?: "",
                entity = it.entity,
                entity_our = it.entity_our,
                specs = if ( it.specs != null) SpecsModel( id = it.specs!!.id,

                    company_id = it.specs!!.company_id, text = it.specs!!.text) else null*/
            )

        }

        return newList

    }

    override suspend fun getOutgoingCRM(): List<ApiResponseCRMModel> {

        crmClient.init(sharedPrefsApi.getToken() ?: "")

        val newList = crmClient.getOutgoingCRM().map {

            ApiResponseCRMModel(
                id = it.id,
                service_id = it.service_id,
                company_id = it.company_id,
                project_id = it.project_id,
                organization_id = it.organization_id,
                cargos = it.cargos?.map {

                    CargoModel(

                        id = it.id,
                        company_id = it.company_id,
                        name = it.name,
                        ui = it.ui

                    )
                },
                user_id = it.user_id,
                active_company_id = it.active_company_id,
                company_work = it.company_work ?: "",
                arenda_id = it.arenda_id,
                status = it.status ?: "",
                text = it.text ?: "",
                ui = it.ui ?: "",
                active = it.active,
                flesh = it.flesh,
                del = it.del,
                nomer = it.nomer,
                statusid = it.statusid,
                data = it.data ?: "",
                date_start = it.date_start ?: "",
                date_stop = it.date_stop ?: "",
                price = it.price,
                template = it.template,
                status_pay = it.status_pay ?: "",
                template_value = it.template_value ?: "",
                task = it.task ?: "",
                created_at = it.created_at,
                updated_at = it.updated_at,
                entity_id = it.entity_id,
                group_entity_id = it.group_entity_id,
                check = it.check,
                log_check = it.log_check ?: "",
                check_our = it.check_our,
                log_check_our = it.log_check_our ?: 0,
                our_entity_id = it.our_entity_id,
                cafe_type = it.cafe_type,
                resource_id = it.resource_id,
                cafe_id = it.cafe_id,
                invoice_pay = it.invoice_pay ?: "",
                type_pay = it.type_pay,
                specification_id = it.specification_id,
                account_entity_id = it.account_entity_id,
                verify_pay = it.verify_pay ?: "",
                system = it.system,
                search_worker = it.search_worker,
                no_view_client = it.no_view_client,
                is_order_creater = it.is_order_creater,
                to_local_id = it.to_local_id,
                from_local_id = it.from_local_id,
                entity_our = if (it.entity_our != null) EntityOurModel(
                    id = it.entity_our!!.id,
                    own = it.entity_our!!.own,
                    contragent_id = it.entity_our!!.contragent_id,
                    company_id = it.entity_our!!.company_id, name = it.entity_our!!.name
                ) else null,
                specs = if (it.specs != null) SpecsModel(
                    id = it.specs!!.id,

                    company_id = it.specs!!.company_id, text = it.specs!!.text
                ) else null,

                entity = if (it.entity != null) EntityModel(
                    id = it.entity!!.id,
                    own = it.entity!!.own, creater_id = it.entity!!.creater_id,
                    name = it.entity!!.name
                ) else null,

                projects = if (it.projects != null) ProjectModel(
                    id = it.projects!!.id,
                    name = it.projects!!.name
                ) else null,

                groupentits = if (it.groupentits != null) GroupEntityModel(

                    id = it.groupentits!!.id,
                    name = it.groupentits!!.name

                ) else null,

                service = if (it.service != null) ServiceModel(

                    id = it.service!!.id,
                    name = it.service!!.name,
                    text = it.service!!.text,
                    doc = it.service!!.doc,
                    ui = it.service!!.ui,
                    comp_project = it.service!!.comp_project,
                    items_value = if ( it.service!!.items_value != null ) it.service!!.items_value!!.map {

                        ItemValueModel(

                            id = it.id,
                            service_id = it.service_id,
                            name = it.name,
                            type = it.type,
                            metka = it.metka,
                            search = it.search,
                            req = it.req,
                            created_at = it.created_at,
                            updated_at = it.updated_at

                        )

                    } else null,


                ) else null,

                value = it.`val`?.map { item ->
                    ValModel(
                        id = item.id,
                        arenda_id = item.arenda_id,
                        type_id = item.type_id,
                        `val` = ValDetailOrStringModel(

                            detail = ValDetailModel(
                                id = item.`val`?.detail?.id ?: 0,
                                name = item.`val`?.detail?.name,
                                text = item.`val`?.detail?.text,
                                email = item.`val`?.detail?.email,
                                email_verified_at = item.`val`?.detail?.email_verified_at,
                                phone = item.`val`?.detail?.phone,
                                ui = item.`val`?.detail?.ui,
                                policy = item.`val`?.detail?.policy,
                                created_at = item.`val`?.detail?.created_at,
                                updated_at = item.`val`?.detail?.updated_at,
                                tema = item.`val`?.detail?.tema,
                                active = item.`val`?.detail?.active,
                                inn = item.`val`?.detail?.inn,
                                image = item.`val`?.detail?.image,
                                contragents = item.`val`?.detail?.contragents,
                                price = item.`val`?.detail?.price,
                                lang_id = item.`val`?.detail?.lang_id


                            ),
                            raw = item.`val`?.raw

                        ),
                        created_at = item.created_at,
                        updated_at = item.updated_at,
                        items_type = ItemsTypeModel(
                            id = item.items_type?.id ?: 0, // Если null, подставляется 0
                            service_id = item.items_type?.service_id ?: 0,
                            name = item.items_type?.name ?: "",
                            type = item.items_type?.type ?: "",
                            metka = item.items_type?.metka ?: "",
                            search = item.items_type?.search ?: 0,
                            req = item.items_type?.req ?: 0,
                            created_at = item.items_type?.created_at ?: "",
                            updated_at = item.items_type?.updated_at ?: ""
                        )
                    )
                } ?: emptyList()


            )

        }

        return newList

    }

    override suspend fun getServices(): List<ServiceResponseModel> {

        servicesClient.init(sharedPrefsApi.getToken() ?: "")

        val newList = servicesClient.getServices().map {

            ServiceResponseModel(

                id = it.id,
                name = it.name,
                text = it.text,
                doc = it.doc,
                ui = it.ui,
                system = it.system,
                width = it.width,
                height = it.height,
                service_type_doc = it.service_type_doc,
                comp_project = it.comp_project,
                view = it.view,
                view_comp_project = it.view_comp_project,
                store = it.store,
                delete_status = it.delete_status,
                company_id = it.company_id,
                created_at = it.created_at,
                updated_at = it.updated_at,
                need_check = it.need_check,
                check_entity_id = it.check_entity_id,
                check_group_entity_id = it.check_group_entity_id,
                need_check_our = it.need_check_our,
                limit_group_entity_id = it.limit_group_entity_id,
                default_entity_id = it.default_entity_id,
                background = it.background,
                image = it.image,
                items = it.items!!.map {

                    ServiceItemModel(

                        id = it.id,

                        service_id = it.service_id,

                        name = it.name,

                        type = it.type,

                        metka = it.metka,

                        search = it.search,

                        req = it.req,

                        created_at = it.created_at,

                        updated_at = it.updated_at

                    )

                }


            )

        }

        return newList

    }

    override suspend fun getSpecifications(): List<SpecificResponseModel> {

        specificationsClient.init(sharedPrefsApi.getToken() ?: "")

        val newList = specificationsClient.getSpecifications().map {

            SpecificResponseModel(

                id = it.id,
                company_id = it.company_id,
                text = it.text,
                ui = it.ui,
                price = it.price,
                status = it.status,
                valuta_id = it.valuta_id,
                created_at = it.created_at,
                updated_at = it.updated_at,
                local_store_id = it.local_store_id,
                entity_id = it.entity_id,
                specification_id = it.specification_id,
                spec_item = it.spec_item!!.map {

                    SpecItemModel(

                        id = it.id,
                        spec_id = it.spec_id,
                        product_id = it.product_id,
                        price_id = it.price_id,
                        block = it.block,
                        count = it.count,
                        price = it.price,
                        nds = it.nds,
                        text = it.text,
                        created_at = it.created_at,
                        updated_at = it.updated_at,
                        cafe_send = it.cafe_send,
                        spec_item_id = it.spec_item_id,
                        numb = it.numb,
                        work = it.work,
                        sale = it.sale,


                        )

                }

            )

        }

        return newList

    }

    override suspend fun getContragents(): List<ContragentResponseModel> {

        val newList = contragentsClient.getContragents().map {

            ContragentResponseModel(

                id = it.id ?: 0,
                name = it.name ?: "",
                ui = it.ui ?: "",
                own = it.own ?: 0,
                entities = if (it.entits != null) {

                    it.entits!!.map {

                        EntityContragentModel(

                            id = it.id ?: 0,
                            name = it.name ?: "",
                            ui = it.ui ?: ""

                        )

                    }
                } else emptyList()

            )


        }

        return newList

    }

    override suspend fun getUsers(): List<UserCRMModel> {

        usersClient.init(sharedPrefsApi.getToken())

        val newList = usersClient.getUsers().map {

            UserCRMModel(

                id = it.id,
                name = it.name,
                email = it.email,
                phone = it.phone,
                ui = it.ui,
                policy = it.policy,
                tema = it.tema,
                active = it.active,
                inn = it.inn,
                image = it.image,
                contragents = it.contragents,
                price = it.price

            )

        }

        return newList

    }

    override suspend fun getLocations(): List<LocationResponseModel> {

        val newList = locationsClient.getLocations().map {

            LocationResponseModel(

                id = it.id,
                adres = it.adres,
                name = it.name,
                default = it.default,
                ui = it.ui,
                email = it.email,
                phone = it.phone,
                text = it.text,
                contragent = if (it.contragent != null) {

                    ContragentLocationModel(

                        id = it.contragent!!.id ?: 0,
                        name = it.contragent!!.name ?: "",
                        ui = it.contragent!!.ui ?: "",
                        own = it.contragent!!.own ?: 0

                    )
                } else null

            )

        }

        return newList

    }

    override suspend fun getProjects(): List<ProjectResponseModel> {

        projectsClient.init(sharedPrefsApi.getToken() ?: "")

        return projectsClient.getProjects().map {

            ProjectResponseModel(

                id = it.id,
                name = it.name,
                creater_id = it.creater_id,
                created_at = it.created_at,
                updated_at = it.updated_at,
                company_id = it.company_id,
                entity_id = it.entity_id,
                active = it.active,
                entity_client_id = it.entity_client_id

            )

        }

    }

    override suspend fun createCRM(

        serviceId: Int?,
        statusPay: Int?,
        verifyPay: Int?,
        task: String?,
        to_local_id: Int?,
        group_entity_id: Int?,
        from_local_id: Int?,
        status: String?,
        price: String?,
        arendaId: Int?,
        specificationId: Int?,
        projectId: Int?,
        entityId: Int?,
        ourEntityId: Int?,
        text: String?,
        statusId: Int?,
        items: List<ServiceItemCreateCRMModel>?

    ) {

        crmClient.init(sharedPrefsApi.getToken() ?: "")

        crmClient.createCRM(serviceId, statusPay, verifyPay, task, to_local_id, group_entity_id,

            from_local_id, status, price, arendaId, specificationId, projectId, entityId,

            ourEntityId, text,

            statusId, items = items!!.map {

                ServiceItem(

                    type_id = it.type_id,

                    name = it.name

                )

            })

    }

    override suspend fun getCargo(): List<CargoResponseModel> {

        cargoClient.init(sharedPrefsApi.getToken() ?: "")

        val newList = cargoClient.getCargo().map {

            CargoResponseModel(

                id = it.id,
                company_id = it.company_id,
                name = it.name,
                status = it.status,
                number = it.number,
                ui = it.ui,
                text = it.text,
                from = it.from,
                to = it.to ?: "",
                from_point = it.from_point,
                to_point = it.to_point,
                cargo_type_id = it.cargo_type_id,
                archive = it.archive,
                to_local = it.to_local,
                from_local = it.from_local,
                to_local_id = it.to_local_id,
                cargo_type = it.cargo_type,
                created_at = it.created_at,
                updated_at = it.updated_at,
                delivery = it.delivery,
                delivery_id = it.delivery_id,
                from_local_id = it.from_local_id

            )

        }

        return newList

    }

    override suspend fun getGroupEntity(): List<GroupEntityResponseModel> {

        groupEntityClient.init(sharedPrefsApi.getToken() ?: "")

        val newList = groupEntityClient.getGroupEntity().map {

            GroupEntityResponseModel(

                id = it.id,
                name = it.name,
                company_id = it.company_id,
                ui = it.ui,
                created_at = it.created_at,
                updated_at = it.updated_at

            )

        }

        return newList

    }

    override suspend fun updateCRM(
        ui: String,
        serviceId: Int?,
        statusPay: Int?,
        verifyPay: Int?,
        task: String?,
        to_local_id: Int?,
        group_entity_id: Int?,
        from_local_id: Int?,
        status: String?,
        price: String?,
        arendaId: Int?,
        specificationId: Int?,
        projectId: Int?,
        entityId: Int?,
        ourEntityId: Int?,
        text: String?,
        statusId: Int?,
        items: List<ServiceItemCreateCRMModel>?
    ) {


        crmClient.init(sharedPrefsApi.getToken() ?: "")

        crmClient.updateCRM(ui, serviceId, statusPay, verifyPay, task, to_local_id,

            group_entity_id, from_local_id, status, price, arendaId, specificationId, projectId,

            entityId, ourEntityId, text, statusId, items = items!!.map {

                ServiceItem(

                    type_id = it.type_id,

                    name = it.name,

                    )

            })

    }

    override suspend fun getProducts(): List<ProductModel> {

        return ProductApiClient(sharedPrefsApi.getToken() ?: "").getProductNames().map {

            ProductModel(

                id = it.id,
                name = it.name,
                text = it.text,
                price = it.price,
                sku = it.sku,
                ui = it.ui

            )

        }

    }

}