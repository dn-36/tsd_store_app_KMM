package domain.repository

interface CRMClientApi {

    suspend fun getIncomingCRM (): String

}