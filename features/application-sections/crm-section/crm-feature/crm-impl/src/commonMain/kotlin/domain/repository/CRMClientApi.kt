package domain.repository

import model.ApiResponseCRMModel

interface CRMClientApi {

    suspend fun getIncomingCRM (): List<ApiResponseCRMModel>
    suspend fun getOutgoingCRM (): List<ApiResponseCRMModel>

}