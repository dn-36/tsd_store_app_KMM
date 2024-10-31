package domain.repository

import com.project.network.crm_network.model.ServiceItem
import model.ApiResponseCRMModel
import model.EntityContragentModel
import model.LocationResponseModel
import model.ServiceResponseModel
import model.SpecificResponseModel
import model.UserCRMModel

interface CRMClientApi {

    suspend fun getIncomingCRM(): List<ApiResponseCRMModel>
    suspend fun getOutgoingCRM(): List<ApiResponseCRMModel>
    suspend fun getSpecifications(): List<SpecificResponseModel>
    suspend fun getServices(): List<ServiceResponseModel>
    suspend fun getUsers(): List<UserCRMModel>
    suspend fun getLegalEntities(): List<EntityContragentModel>
    suspend fun getLocations(): List<LocationResponseModel>
    suspend fun getProjects(): String
   /* suspend fun createCRM (

        serviceId: Int?,
        statusPay: Int?,
        verifyPay: Int?,
        task: String?,
        price: String?,
        arendaId: Int?,
        specificationId: Int?,
        projectId: Int?,
        entityId: Int?,
        ourEntityId: Int?,
        text: String?,
        statusId: Int?,
        items: List<ServiceItem>? = listOf()
    )*/

}