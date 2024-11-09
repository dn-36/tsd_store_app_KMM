package component.data_entry.viewmodel

import model.ApiResponseCRMModel
import model.CargoResponseModel
import model.ContragentResponseModel
import model.EntityContragentModel
import model.GroupEntityResponseModel
import model.LocationResponseModel
import model.ProjectResponseModel
import model.ServiceResponseModel
import model.SpecificResponseModel
import model.UserCRMModel

sealed class DataEntryIntents {

    object MenuLegalEntityPerformer : DataEntryIntents()

    object MenuLegalEntity : DataEntryIntents()

    object MenuProject : DataEntryIntents()

    object MenuStatus : DataEntryIntents()

    object MenuGroupEntity : DataEntryIntents()

    object MenuVerified : DataEntryIntents()

    object MenuPaidFor : DataEntryIntents()

    object MenuLocationsPerformer : DataEntryIntents()

    object MenuLocations : DataEntryIntents()

    object MenuServices : DataEntryIntents()

    object MenuSpecifications : DataEntryIntents()

    object MenuEmployeePerformer : DataEntryIntents()

    object MenuEmployee : DataEntryIntents()

    object MenuCargo : DataEntryIntents()


    data class SelectGroupEntity(val item: GroupEntityResponseModel) : DataEntryIntents()

    data class SelectProject(val item: ProjectResponseModel) : DataEntryIntents()

    data class SelectService(val item: ServiceResponseModel, val list: List<ContragentResponseModel>) : DataEntryIntents()

    data class SelectLocationPerformer(val item: LocationResponseModel) : DataEntryIntents()

    data class SelectLocation(val item: LocationResponseModel) : DataEntryIntents()

    data class SelectLegalEntityPerformer(

        val entity: EntityContragentModel,

        val contargent: ContragentResponseModel

    ) : DataEntryIntents()

    data class SelectLegalEntity(

        val entity: EntityContragentModel,

        val contargent: ContragentResponseModel

    ) : DataEntryIntents()

    data class SelectEmployeePerformer(val item: UserCRMModel) : DataEntryIntents()

    data class SelectEmployee(val item: UserCRMModel) : DataEntryIntents()

    data class SelectStatus(val item: String, val index:Int) : DataEntryIntents()

    data class SelectPaidFor(val item: String, val index:Int) : DataEntryIntents()

    data class SelectVerified(val item: String, val index:Int) : DataEntryIntents()

    data class SelectSpecification(val item: SpecificResponseModel) : DataEntryIntents()

    data class SelectCargo(val item: CargoResponseModel) : DataEntryIntents()



    object DeleteSelectedSpecification : DataEntryIntents()

    object DeleteSelectedProject : DataEntryIntents()

    object DeleteSelectedStatus : DataEntryIntents()

    object DeleteSelectedEmployeePerformer : DataEntryIntents()

    object DeleteSelectedEmployee : DataEntryIntents()

    object DeleteSelectedLegalEntityPerformer : DataEntryIntents()

    object DeleteSelectedLegalEntity : DataEntryIntents()

    object DeleteSelectedLocationPerformer : DataEntryIntents()

    object DeleteSelectedLocation : DataEntryIntents()

    object DeleteSelectedService : DataEntryIntents()

    object DeleteSelectedPaidFor : DataEntryIntents()

    object DeleteSelectedVerified : DataEntryIntents()

    object DeleteSelectedGroupEntity : DataEntryIntents()

    object DeleteSelectedCargo : DataEntryIntents()



    data class InputTextService(

        val text: String,

        val list: List<ServiceResponseModel>

    ) : DataEntryIntents()

    data class InputTextProject(

        val text: String,

        val list: List<ProjectResponseModel>

    ) : DataEntryIntents()

    data class InputTextGroupEntity(

        val text: String,

        val list: List<GroupEntityResponseModel>

    ) : DataEntryIntents()

    data class InputTextSpecification(

        val text: String,

        val list: List<SpecificResponseModel>

    ) : DataEntryIntents()

    data class InputTextEmployeePerformer(

        val text: String,

        val list: List<UserCRMModel>

    ) : DataEntryIntents()

    data class InputTextLocationPerformer(

        val text: String,

        val list: List<LocationResponseModel>

    ) : DataEntryIntents()

    data class InputTextLegalEntityPerformer(

        val text: String,

        val list: List<ContragentResponseModel>

    ) : DataEntryIntents()

    data class InputTextEmployee (

        val text: String,

        val list: List<UserCRMModel>

    ) : DataEntryIntents()

    data class InputTextLocation (

        val text: String,

        val list: List<LocationResponseModel>

    ) : DataEntryIntents()

    data class InputTextLegalEntity (

        val text: String,

        val list: List<ContragentResponseModel>

    ) : DataEntryIntents()

    data class InputTextCargo(

        val text: String,

        val list: List<CargoResponseModel>

    ) : DataEntryIntents()

    data class InputTextStatusText(val text: String) : DataEntryIntents()

    data class InputTextAdditionalFields(val text: String, val index: Int ) : DataEntryIntents()

    data class InputTextTask(val text: String) : DataEntryIntents()


    data class SetScreen(

        val listSpecifications: List<SpecificResponseModel>,

        val listServices: List<ServiceResponseModel>,

        val listEmployee: List<UserCRMModel>,

        val listLegalEntities: List<ContragentResponseModel>,

        val listLocations: List<LocationResponseModel>,

        val listCargo: List<CargoResponseModel>,

        val listGroupEntity: List<GroupEntityResponseModel>,

        val listProjects: List<ProjectResponseModel>,

        val item: ApiResponseCRMModel?

    ) : DataEntryIntents()


    object TotalPrice: DataEntryIntents()


    object ColorTF: DataEntryIntents()

}