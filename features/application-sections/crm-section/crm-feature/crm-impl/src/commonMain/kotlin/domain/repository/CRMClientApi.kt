package domain.repository

import model.ApiResponseCRMModel
import model.CargoResponseModel
import model.ContragentResponseModel
import model.LocationResponseModel
import model.ServiceItemCreateCRMModel
import model.ServiceResponseModel
import model.SpecificResponseModel
import model.UserCRMModel

interface CRMClientApi {

    suspend fun getIncomingCRM(): List<ApiResponseCRMModel>
    suspend fun getOutgoingCRM(): List<ApiResponseCRMModel>
    suspend fun getSpecifications(): List<SpecificResponseModel>
    suspend fun getServices(): List<ServiceResponseModel>
    suspend fun getUsers(): List<UserCRMModel>
    suspend fun getCargo(): List<CargoResponseModel>
    suspend fun getContragents(): List<ContragentResponseModel>
    suspend fun getLocations(): List<LocationResponseModel>
    suspend fun getProjects(): String
    suspend fun createCRM (

        serviceId: Int?,
        statusPay: Int?,
        verifyPay: Int?,
        task: String?,
        status: String?,
        price: String?,
        arendaId: Int?,
        specificationId: Int?,
        projectId: Int?,
        entityId: Int?,
        ourEntityId: Int?,
        text: String?,
        statusId: Int?,
        items: List<ServiceItemCreateCRMModel>? = listOf()
    )

}