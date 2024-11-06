package domain.usecases

import domain.repository.CRMClientApi
import model.ServiceItemCreateCRMModel

class UpdateCRMUseCase (

    private val client: CRMClientApi,

    ) {

    suspend fun execute(

        ui:String,
        serviceId: Int?,
        statusPay: Int?,
        verifyPay: Int?,
        task: String?,
        to_local_id:Int?,
        group_entity_id:Int?,
        from_local_id:Int?,
        status: String?,
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

        client.updateCRM(

            ui, serviceId, statusPay, verifyPay, task, to_local_id, group_entity_id, from_local_id,

            status, price, arendaId, specificationId, projectId, entityId, ourEntityId, text,

            statusId, items
        )
    }
}