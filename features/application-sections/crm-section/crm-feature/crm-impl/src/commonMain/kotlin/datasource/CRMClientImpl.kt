package datasource

import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.crm_network.CRMClient
import domain.repository.CRMClientApi
import model.ActiveModel
import model.ApiResponseCRMModel

class CRMClientImpl(

    private val crmClient: CRMClient,

    private val sharedPrefsApi: SharedPrefsApi

) : CRMClientApi {

    override suspend fun getIncomingCRM(): List<ApiResponseCRMModel> {

        crmClient.init(sharedPrefsApi.getToken() ?: "")

        var newList = emptyList<ApiResponseCRMModel>()

        newList = crmClient.getIncomingCRM().map {

            ApiResponseCRMModel(

                id = it.id,
                service_id = it.service_id,
                company_id = it.company_id,
                project_id = it.project_id,
                organization_id = it.organization_id,
                user_id = it.user_id,
                active_company_id = it.active_company_id,
                company_work = it.company_work,
                arenda_id = it.arenda_id,
                status = it.status,
                text = it.text,
                ui = it.ui,
                active = if (it.active != null) ActiveModel( id = it.active!!.id ,

                    service_id = it.active!!.service_id ) else null ,

                flesh = it.flesh,
                del = it.del,
                nomer = it.nomer,
                statusid = it.statusid,
                data = it.data,  // Если "data" содержит сложные данные, можно указать точный тип
                date_start = it.date_start,  // Можно использовать `LocalDate` если нужна дата
                date_stop = it.date_stop,
                price = it.price,
                template = it.template,
                status_text = it.status_text

            )

        }

        return newList

    }

    override suspend fun getOutgoingCRM(): String {

        return crmClient.getOutgoingCRM()

    }

}