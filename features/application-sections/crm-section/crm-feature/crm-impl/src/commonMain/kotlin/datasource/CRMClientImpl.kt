package datasource

import com.project.network.crm_network.CRMClient
import domain.repository.CRMClientApi

class CRMClientImpl (

    private val crmClient: CRMClient

) : CRMClientApi {

    override suspend fun getIncomingCRM(): String {

        return crmClient.getIncomingCRM()

    }

}