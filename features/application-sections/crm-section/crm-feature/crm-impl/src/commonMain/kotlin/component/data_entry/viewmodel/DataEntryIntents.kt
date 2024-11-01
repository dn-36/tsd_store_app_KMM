package component.data_entry.viewmodel

import model.EntityContragentModel
import model.LocationResponseModel
import model.ServiceResponseModel
import model.SpecificResponseModel
import model.UserCRMModel

sealed class DataEntryIntents {

    object MenuLegalEntityPerformer : DataEntryIntents()

    object MenuStatus : DataEntryIntents()

    object MenuLocations : DataEntryIntents()

    object MenuServices : DataEntryIntents()

    object MenuSpecifications : DataEntryIntents()

    object MenuEmployee : DataEntryIntents()

    object MenuGoodsAndServices : DataEntryIntents()


    data class SelectGoodsAndServices(val item: SpecificResponseModel) : DataEntryIntents()

    data class SelectService(val item: ServiceResponseModel) : DataEntryIntents()

    data class SelectLocation(val item: LocationResponseModel) : DataEntryIntents()

    data class SelectLegalEntityPerformer(val item: EntityContragentModel) : DataEntryIntents()

    data class SelectEmployee(val item: UserCRMModel) : DataEntryIntents()

    data class SelectStatus(val item: String, val index:Int) : DataEntryIntents()

    data class SelectSpecification(val item: SpecificResponseModel) : DataEntryIntents()


    object DeleteSelectedGoodsAndServices : DataEntryIntents()

    object DeleteSelectedSpecification : DataEntryIntents()

    object DeleteSelectedStatus : DataEntryIntents()

    object DeleteSelectedEmployee : DataEntryIntents()

    object DeleteSelectedLegalEntityPerformer : DataEntryIntents()

    object DeleteSelectedLocation : DataEntryIntents()

    object DeleteSelectedService : DataEntryIntents()


    data class InputTextEmployee(

        val text: String,

        val list: List<UserCRMModel>

    ) : DataEntryIntents()

    data class InputTextService(

        val text: String,

        val list: List<ServiceResponseModel>

    ) : DataEntryIntents()

    data class InputTextSpecification(

        val text: String,

        val list: List<SpecificResponseModel>

    ) : DataEntryIntents()

    data class InputTextLocation(

        val text: String,

        val list: List<LocationResponseModel>

    ) : DataEntryIntents()

    data class InputTextLegalEntityPerformer(

        val text: String,

        val list: List<EntityContragentModel>

    ) : DataEntryIntents()

    data class InputTextStatus(

        val text: String,

        val list: List<String>

    ) : DataEntryIntents()

    data class InputTextGoodsAndServices(

        val text: String,

        val list: List<SpecificResponseModel>

    ) : DataEntryIntents()

    data class InputTextComment(val text: String) : DataEntryIntents()

    data class InputTextStatusText(val text: String) : DataEntryIntents()

    data class InputTextPhoneNumber(val text: String) : DataEntryIntents()

    data class InputTextFIO(val text: String) : DataEntryIntents()

    data class InputTextTask(val text: String) : DataEntryIntents()


    data class SetScreen(

        val listSpecifications: List<SpecificResponseModel>,

        val listServices: List<ServiceResponseModel>,

        val listEmployee: List<UserCRMModel>,

        val listLegalEntities: List<EntityContragentModel>,

        val listLocations: List<LocationResponseModel>

    ) : DataEntryIntents()


    object TotalPrice: DataEntryIntents()

}