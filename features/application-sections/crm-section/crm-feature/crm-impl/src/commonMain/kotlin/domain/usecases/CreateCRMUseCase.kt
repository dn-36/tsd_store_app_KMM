package domain.usecases

import domain.repository.CRMClientApi
import model.ServiceItemCreateCRMModel

class CreateCRMUseCase(

    private val client: CRMClientApi,

    ) {

    suspend fun execute(

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
        items: List<ServiceItemCreateCRMModel>?

    ) {

        client.createCRM(
            serviceId, statusPay, verifyPay, task, price, arendaId, specificationId,

            projectId, entityId,

            ourEntityId, text, statusId, items
        )
    }
}