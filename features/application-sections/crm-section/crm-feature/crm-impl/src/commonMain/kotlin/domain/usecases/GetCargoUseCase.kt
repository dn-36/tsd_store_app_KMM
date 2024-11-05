package domain.usecases

import domain.repository.CRMClientApi
import model.CargoResponseModel
import model.ContragentResponseModel

class GetCargoUseCase (

    private val client: CRMClientApi,

    ) {

    suspend fun execute ( ): List<CargoResponseModel> {

        return client.getCargo ()
    }
}