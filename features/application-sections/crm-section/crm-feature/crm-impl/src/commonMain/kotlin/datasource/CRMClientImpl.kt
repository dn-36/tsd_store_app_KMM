package datasource

import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.contragent_network.ContragentClient
import com.project.network.crm_network.CRMClient
import com.project.network.locations_network.LocationsClient
import com.project.network.projects_network.ProjectsClient
import com.project.network.services_network.ServicesClient
import com.project.network.specifications_network.SpecificationsClient
import com.project.network.users_network.UsersClient
import domain.repository.CRMClientApi
import model.ApiResponseCRMModel
import model.ContragentResponseModel
import model.EntityContragentModel
import model.EntityModel
import model.EntityOurModel
import model.LocationResponseModel
import model.ProjectModel
import model.ServiceResponseModel
import model.SpecificResponseModel
import model.SpecsModel
import model.UserCRMModel

class CRMClientImpl(

    private val crmClient: CRMClient,

    private val sharedPrefsApi: SharedPrefsApi,

    private val specificationsClient: SpecificationsClient,

    private val servicesClient: ServicesClient,

    private val usersClient: UsersClient,

    private val contragentsClient: ContragentClient,

    private val locationsClient: LocationsClient,

    private val projectsClient: ProjectsClient

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
                entity_our = if ( it.entity_our != null ) EntityOurModel( id = it.entity_our!!.id,
                    own = it.entity_our!!.own,
                    contragent_id = it.entity_our!!.contragent_id,
                    company_id = it.entity_our!!.company_id, name = it.entity_our!!.name) else null,
                specs = if ( it.specs != null) SpecsModel( id = it.specs!!.id,

                    company_id = it.specs!!.company_id, text = it.specs!!.text) else null,

                entity = if ( it.entity != null) EntityModel( id = it.entity!!.id,
                    own = it.entity!!.own, creater_id = it.entity!!.creater_id,
                    name = it.entity!!.name ) else null,

                projects = if ( it.projects != null) ProjectModel( id = it.projects!!.id,
                    name = it.projects!!.name ) else null
                /*projects = it.projects,
                service = it.service,
                company = it.company,
                companactiv = it.companactiv ?: "",
                entity = it.entity,
                groupentits = it.groupentits,
                entity_our = it.entity_our,
                specs = if ( it.specs != null) SpecsModel( id = it.specs!!.id,

                    company_id = it.specs!!.company_id, text = it.specs!!.text) else null*/
            )

        }

        return newList

    }

    override suspend fun getOutgoingCRM(): List<ApiResponseCRMModel> {

        crmClient.init(sharedPrefsApi.getToken() ?: "")

        val newList  = crmClient.getOutgoingCRM().map {

            ApiResponseCRMModel(
                id = it.id,
                service_id = it.service_id,
                company_id = it.company_id,
                project_id = it.project_id,
                organization_id = it.organization_id,
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
                entity_our = if ( it.entity_our != null ) EntityOurModel( id = it.entity_our!!.id,
                    own = it.entity_our!!.own,
                    contragent_id = it.entity_our!!.contragent_id,
                    company_id = it.entity_our!!.company_id, name = it.entity_our!!.name) else null,
                specs = if ( it.specs != null) SpecsModel( id = it.specs!!.id,

                    company_id = it.specs!!.company_id, text = it.specs!!.text) else null,

                entity = if ( it.entity != null) EntityModel( id = it.entity!!.id,
                    own = it.entity!!.own, creater_id = it.entity!!.creater_id,
                    name = it.entity!!.name ) else null,

                projects = if ( it.projects != null) ProjectModel( id = it.projects!!.id,
                    name = it.projects!!.name ) else null

            )

        }

        return newList

    }

    override suspend fun getServices(): List<ServiceResponseModel> {

        servicesClient.init(sharedPrefsApi.getToken() ?: "")

        val newList  = servicesClient.getServices().map {

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
             image = it.image


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
                specification_id = it.specification_id

            )

        }

        return newList

    }

    override suspend fun getLegalEntities(): List<EntityContragentModel> {

        var newList = contragentsClient.getContragents().flatMap { item ->

            item.entits?.map {
                EntityContragentModel(
                    id = it.id,
                    name = it.name,
                    ui = it.ui
                )
            } ?: emptyList() // Если entits == null, возвращаем пустой список
        }

        return newList

    }

    override suspend fun getUsers(): List<UserCRMModel> {

        usersClient.init(sharedPrefsApi.getToken())

        val newList  = usersClient.getUsers().map {

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
                text = it.text

            )

        }

        return newList

    }

    override suspend fun getProjects(): String {

        projectsClient.init(sharedPrefsApi.getToken()?: "")

        return projectsClient.getProjects()

    }

}