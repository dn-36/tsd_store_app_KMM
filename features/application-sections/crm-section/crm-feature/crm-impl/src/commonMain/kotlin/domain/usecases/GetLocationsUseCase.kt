package domain.usecases

import domain.repository.CRMClientApi
import model.ApiResponseCRMModel
import model.LocationResponseModel

class GetLocationsUseCase (

    private val client: CRMClientApi,

    ) {

    suspend fun execute ( ): List<LocationResponseModel> {

        return client.getLocations ()
    }
}