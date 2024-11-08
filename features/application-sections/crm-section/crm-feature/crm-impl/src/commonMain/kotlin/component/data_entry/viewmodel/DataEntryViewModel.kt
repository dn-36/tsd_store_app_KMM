package component.data_entry.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
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
import model.ValDetailModel

class DataEntryViewModel (): ViewModel() {

    var state by mutableStateOf(DataEntryState())

    fun processIntents ( intent: DataEntryIntents ) {

        when ( intent ) {

            is DataEntryIntents.MenuEmployeePerformer -> menuEmployeePerformer()

            is DataEntryIntents.MenuProject -> menuProject()

            is DataEntryIntents.MenuGroupEntity -> menuGroupEntity()

            is DataEntryIntents.MenuEmployee -> menuEmployee()

            is DataEntryIntents.MenuLocationsPerformer -> menuLocationsPerformer()

            is DataEntryIntents.MenuLocations -> menuLocations()

            is DataEntryIntents.MenuPaidFor -> menuPaidFor()

            is DataEntryIntents.MenuVerified -> menuVerified()

            is DataEntryIntents.MenuLegalEntityPerformer -> menuLegalEntityPerformer()

            is DataEntryIntents.MenuLegalEntity -> menuLegalEntity()

            is DataEntryIntents.MenuServices -> menuServices()

            is DataEntryIntents.MenuSpecifications -> menuSpecifications()

            is DataEntryIntents.MenuStatus -> menuStatus()

            is DataEntryIntents.MenuCargo -> menuCargo()


            is DataEntryIntents.SelectEmployeePerformer -> selectEmployeePerformer( intent.item )

            is DataEntryIntents.SelectProject -> selectProject( intent.item )

            is DataEntryIntents.SelectEmployee -> selectEmployee( intent.item )

            is DataEntryIntents.SelectGroupEntity-> selectGroupEntity( intent.item )

            is DataEntryIntents.SelectLocationPerformer -> selectLocationPerformer( intent.item )

            is DataEntryIntents.SelectLocation -> selectLocation( intent.item )

            is DataEntryIntents.SelectLegalEntityPerformer -> selectLegalEntityPerformer(

                intent.entity, intent.contargent )

            is DataEntryIntents.SelectLegalEntity -> selectLegalEntity(

                intent.entity, intent.contargent )

            is DataEntryIntents.SelectService -> selectService( intent.item )

            is DataEntryIntents.SelectSpecification -> selectSpecific( intent.item )

            is DataEntryIntents.SelectCargo -> selectCargo( intent.item )

            is DataEntryIntents.SelectStatus -> selectStatus( intent.item, intent.index )

            is DataEntryIntents.SelectPaidFor -> selectPaidFor( intent.item, intent.index )

            is DataEntryIntents.SelectVerified -> selectVerified( intent.item, intent.index )


            is DataEntryIntents.DeleteSelectedEmployeePerformer -> deleteSelectedEmployeePerformer()

            is DataEntryIntents.DeleteSelectedEmployee -> deleteSelectedEmployee()

            is DataEntryIntents.DeleteSelectedProject -> deleteSelectedProject()

            is DataEntryIntents.DeleteSelectedGroupEntity -> deleteSelectedGroupEntity()

            is DataEntryIntents.DeleteSelectedPaidFor -> deleteSelectedPaidFor()

            is DataEntryIntents.DeleteSelectedVerified -> deleteSelectedVerified()

            is DataEntryIntents.DeleteSelectedLegalEntityPerformer -> {

                deleteSelectedLegalEntityPerformer()
            }

            is DataEntryIntents.DeleteSelectedLegalEntity -> { deleteSelectedLegalEntity() }

            is DataEntryIntents.DeleteSelectedLocationPerformer -> deleteSelectedLocationPerformer()

            is DataEntryIntents.DeleteSelectedLocation -> deleteSelectedLocation()

            is DataEntryIntents.DeleteSelectedSpecification -> deleteSelectedSpecific()

            is DataEntryIntents.DeleteSelectedStatus -> deleteSelectedStatus()

            is DataEntryIntents.DeleteSelectedService -> deleteSelectedService()

            is DataEntryIntents.DeleteSelectedCargo -> deleteSelectedCargo()


            is DataEntryIntents.InputTextLocationPerformer -> inputTextLocationPerfomer(

                intent.text, intent.list )

            is DataEntryIntents.InputTextCargo -> inputTextCargo( intent.text, intent.list )

            is DataEntryIntents.InputTextProject -> inputTextProject( intent.text, intent.list )

            is DataEntryIntents.InputTextGroupEntity -> inputTextGroupEntity( intent.text, intent.list )

            is DataEntryIntents.InputTextLocation -> inputTextLocation( intent.text, intent.list )

            is DataEntryIntents.InputTextAdditionalFields -> inputTextAdditionalFields(

                intent.text, intent.index )

            is DataEntryIntents.InputTextLegalEntityPerformer -> inputTextLegalEntityPerformer(

                intent.text, intent.list )

            is DataEntryIntents.InputTextLegalEntity -> inputTextLegalEntity(

                intent.text, intent.list )

            is DataEntryIntents.InputTextService -> inputTextService( intent.text, intent.list )

            is DataEntryIntents.InputTextSpecification -> inputTextSpecific( intent.text,

                intent.list )

            is DataEntryIntents.InputTextEmployeePerformer -> inputTextEmployeePerfomer( intent.text, intent.list )

            is DataEntryIntents.InputTextEmployee -> inputTextEmployee( intent.text, intent.list )

            is DataEntryIntents.InputTextStatusText -> inputTextStatusText( intent.text )

            is DataEntryIntents.InputTextTask -> inputTextTask( intent.text )


            is DataEntryIntents.SetScreen -> { setScreen( intent.listSpecifications,

                intent.listServices, intent.listEmployee, intent.listLegalEntities,

                intent.listLocations, intent.listCargo, intent.listGroupEntity, intent.listProjects,

                intent.item)

            }

            is DataEntryIntents.TotalPrice -> totalPrice()


            is DataEntryIntents.ColorTF -> colorTF()


        }

    }

    fun menuServices () {

        state = state.copy(

            expendedService = !state.expendedService,
            expendedStatus = false,
            expendedEmployeePerformer = false,
            expendedLegalEntityPerformer = false,
            expendedSpecifications = false,
            expendedLocationsPerformer = false,
            expendedEmployee = false,
            expendedLocations = false,
            expendedLegalEntity = false,
            expendedProject = false,
            expendedCargo = false

        )

    }

    fun menuProject () {

        state = state.copy(

            expendedProject = !state.expendedProject,
            expendedService = false,
            expendedStatus = false,
            expendedEmployeePerformer = false,
            expendedLegalEntityPerformer = false,
            expendedSpecifications = false,
            expendedLocationsPerformer = false,
            expendedEmployee = false,
            expendedLocations = false,
            expendedLegalEntity = false,
            expendedCargo = false

        )

    }

    fun menuLegalEntityPerformer () {

        state = state.copy(

            expendedLegalEntityPerformer = !state.expendedLegalEntityPerformer,
            expendedStatus = false,
            expendedEmployeePerformer = false,
            expendedSpecifications = false,
            expendedLocationsPerformer = false,
            expendedService = false,
            expendedEmployee = false,
            expendedLocations = false,
            expendedLegalEntity = false,
            expendedProject = false,
            expendedCargo = false

        )

    }

    fun menuLegalEntity () {

        state = state.copy(

            expendedLegalEntity = !state.expendedLegalEntity,
            expendedStatus = false,
            expendedEmployeePerformer = false,
            expendedSpecifications = false,
            expendedLocationsPerformer = false,
            expendedService = false,
            expendedLocations = false,
            expendedEmployee = false,
            expendedLegalEntityPerformer = false,
            expendedProject = false,
            expendedCargo = false

        )

    }

    fun menuEmployeePerformer () {

        state = state.copy(

            expendedEmployeePerformer = !state.expendedEmployeePerformer,
            expendedStatus = false,
            expendedLegalEntityPerformer = false,
            expendedSpecifications = false,
            expendedLocationsPerformer = false,
            expendedService = false,
            expendedEmployee = false,
            expendedLocations = false,
            expendedLegalEntity = false,
            expendedProject = false,
            expendedCargo = false

        )

    }

    fun menuEmployee () {

        state = state.copy(

            expendedEmployee = !state.expendedEmployee,
            expendedStatus = false,
            expendedLegalEntityPerformer = false,
            expendedSpecifications = false,
            expendedLocationsPerformer = false,
            expendedService = false,
            expendedLegalEntity = false,
            expendedLocations = false,
            expendedEmployeePerformer = false,
            expendedProject = false,
            expendedCargo = false

        )

    }

    fun menuGroupEntity () {

        state = state.copy(

            expendedGroupEntity = !state.expendedGroupEntity,
            expendedEmployee = false,
            expendedStatus = false,
            expendedLegalEntityPerformer = false,
            expendedSpecifications = false,
            expendedLocationsPerformer = false,
            expendedService = false,
            expendedLegalEntity = false,
            expendedLocations = false,
            expendedEmployeePerformer = false,
            expendedProject = false,
            expendedCargo = false

        )

    }


    fun menuLocationsPerformer () {

        state = state.copy(

            expendedLocationsPerformer = !state.expendedLocationsPerformer,
            expendedSpecifications = false,
            expendedService = false,
            expendedStatus = false,
            expendedEmployeePerformer = false,
            expendedLegalEntityPerformer = false,
            expendedEmployee = false,
            expendedLocations = false,
            expendedLegalEntity = false,
            expendedProject = false,
            expendedCargo = false

        )

    }

    fun menuLocations () {

        state = state.copy(

            expendedLocations = !state.expendedLocations,
            expendedSpecifications = false,
            expendedService = false,
            expendedStatus = false,
            expendedEmployeePerformer = false,
            expendedLegalEntityPerformer = false,
            expendedEmployee = false,
            expendedLegalEntity = false,
            expendedLocationsPerformer = false,
            expendedProject = false,
            expendedCargo = false

        )

    }

    fun menuStatus () {

        state = state.copy(

            expendedStatus = !state.expendedStatus,
            expendedEmployeePerformer = false,
            expendedLegalEntityPerformer = false,
            expendedService = false,
            expendedSpecifications = false,
            expendedLocationsPerformer = false,
            expendedEmployee = false,
            expendedLocations = false,
            expendedLegalEntity = false,
            expendedProject = false,
            expendedCargo = false


        )

    }

    fun menuSpecifications () {

        state = state.copy(

            expendedSpecifications = !state.expendedSpecifications,
            expendedEmployeePerformer = false,
            expendedStatus = false,
            expendedLegalEntityPerformer = false,
            expendedService = false,
            expendedLocationsPerformer = false,
            expendedEmployee = false,
            expendedLocations = false,
            expendedLegalEntity = false,
            expendedProject = false,
            expendedCargo = false

        )

    }

    fun menuPaidFor () {

        state = state.copy(

            expendedPaidFor = !state.expendedPaidFor,
            expendedVerified = false,
            expendedSpecifications = false,
            expendedEmployeePerformer = false,
            expendedStatus = false,
            expendedLegalEntityPerformer = false,
            expendedService = false,
            expendedLocationsPerformer = false,
            expendedEmployee = false,
            expendedLocations = false,
            expendedLegalEntity = false,
            expendedProject = false,
            expendedCargo = false

        )

    }

    fun menuVerified () {

        state = state.copy(

            expendedVerified = !state.expendedVerified,
            expendedPaidFor = false,
            expendedSpecifications = false,
            expendedEmployeePerformer = false,
            expendedStatus = false,
            expendedLegalEntityPerformer = false,
            expendedService = false,
            expendedLocationsPerformer = false,
            expendedEmployee = false,
            expendedLocations = false,
            expendedLegalEntity = false,
            expendedProject = false,
            expendedCargo = false

        )

    }

    fun menuCargo () {

        state = state.copy(

            expendedCargo = !state.expendedCargo,
            expendedVerified = false,
            expendedPaidFor = false,
            expendedSpecifications = false,
            expendedEmployeePerformer = false,
            expendedStatus = false,
            expendedLegalEntityPerformer = false,
            expendedService = false,
            expendedLocationsPerformer = false,
            expendedEmployee = false,
            expendedLocations = false,
            expendedProject = false,
            expendedLegalEntity = false

        )

    }

    fun selectService ( item: ServiceResponseModel ) {

        val newList = List(if ( item.items != null) item.items.size else 0) { "" }

    state = state.copy(

        selectedService = item,

        expendedService = false,

        textFieldsValues = newList

    )

        println("SELECT SERVICE :  ${state.selectedService}")

    }

    fun selectCargo ( item: CargoResponseModel ) {

        state = state.copy(

            selectedCargo = item,

            expendedCargo = false

        )

    }

    fun selectGroupEntity ( item: GroupEntityResponseModel ) {

        state = state.copy(

            selectedGroupEntity = item,

            expendedGroupEntity = false

        )

    }

    fun selectSpecific ( item: SpecificResponseModel ) {

        state = state.copy(

            selectedSpecific = item,

            expendedSpecifications = false

        )

    }

    fun selectLegalEntityPerformer ( entity: EntityContragentModel,

                                    contragent: ContragentResponseModel ) {

        state = state.copy(

            selectedLegalEntityPerformer = entity,

            selectedContragentPerformer = contragent,

            expendedLegalEntityPerformer = false

        )

    }

    fun selectLegalEntity ( entity: EntityContragentModel,

                                     contragent: ContragentResponseModel ) {

        state = state.copy(

            selectedLegalEntity = entity,

            selectedContragent = contragent,

            expendedLegalEntity = false

        )

    }

    fun selectLocationPerformer (item: LocationResponseModel ) {

        state = state.copy(

            selectedLocationPerformer = item,

            expendedLocationsPerformer = false

        )

    }

    fun selectLocation (item: LocationResponseModel ) {

        state = state.copy(

            selectedLocation = item,

            expendedLocations = false

        )

    }

    fun selectStatus ( item: String, index:Int ) {

        state = state.copy(

            selectedStatus = Pair(item,index),

            expendedStatus = false

        )

    }

    fun selectPaidFor ( item: String, index:Int ) {

        state = state.copy(

            selectedPaidFor = Pair(item,index),

            expendedPaidFor = false

        )

    }

    fun selectVerified ( item: String, index:Int ) {

        state = state.copy(

            selectedVerified = Pair(item,index),

            expendedVerified = false

        )

    }


    fun selectEmployeePerformer (item: UserCRMModel ) {

        state = state.copy(

            selectedEmployeePerformer = item,

            expendedEmployeePerformer = false

        )

    }

    fun selectEmployee (item: UserCRMModel ) {

        state = state.copy(

            selectedEmployee = item,

            expendedEmployee = false

        )

    }

    fun selectProject (item: ProjectResponseModel ) {

        state = state.copy(

            selectedProject = item,

            expendedProject = false

        )

    }

    fun deleteSelectedService ( ) {

        state = state.copy(

            selectedService = null

        )

    }

    fun deleteSelectedCargo ( ) {

        state = state.copy(

            selectedCargo = null

        )

    }

    fun deleteSelectedSpecific ( ) {

        state = state.copy(

            selectedSpecific = null

        )

    }

    fun deleteSelectedGroupEntity ( ) {

        state = state.copy(

            selectedGroupEntity = null

        )

    }

    fun deleteSelectedLegalEntityPerformer ( ) {

        state = state.copy(

            selectedLegalEntityPerformer = null

        )

    }

    fun deleteSelectedLegalEntity ( ) {

        state = state.copy(

            selectedLegalEntity = null

        )

    }

    fun deleteSelectedLocationPerformer ( ) {

        state = state.copy(

            selectedLocationPerformer = null

        )

    }

    fun deleteSelectedLocation ( ) {

        state = state.copy(

            selectedLocation = null

        )

    }

    fun deleteSelectedPaidFor ( ) {

        state = state.copy(

            selectedPaidFor = null

        )

    }

    fun deleteSelectedVerified ( ) {

        state = state.copy(

            selectedVerified = null

        )

    }

    fun deleteSelectedStatus (  ) {

        state = state.copy(

            selectedStatus = Pair("",0)

        )

    }

    fun deleteSelectedEmployeePerformer (  ) {

        state = state.copy(

            selectedEmployeePerformer = null

        )

    }

    fun deleteSelectedEmployee (  ) {

        state = state.copy(

            selectedEmployee = null

        )

    }

    fun deleteSelectedProject (  ) {

        state = state.copy(

            selectedProject = null

        )

    }

    fun inputTextService ( text: String, list: List<ServiceResponseModel> ) {

        state = state.copy(

            service = text,

            filteredListServices = list

        )

    }

    fun inputTextSpecific ( text: String, list: List<SpecificResponseModel> ) {

        state = state.copy(

            specification = text,

            filteredListSpecifications = list

        )

    }

    fun inputTextLocationPerfomer (text: String, list: List<LocationResponseModel> ) {

        state = state.copy(

            locationPerformer = text,

            filteredListLocations = list

        )

    }

    fun inputTextGroupEntity (text: String, list: List<GroupEntityResponseModel> ) {

        state = state.copy(

            groupEntity = text,

            filteredListGroupEntity = list

        )

    }

    fun inputTextLegalEntityPerformer ( text: String, list: List<ContragentResponseModel> ) {

        state = state.copy(

            legalEntityPerformer = text,

            filteredListContragents = list

        )

    }

    fun inputTextCargo ( text: String, list: List<CargoResponseModel> ) {

        state = state.copy(

            cargo = text,

            filteredListCargo = list

        )

    }


    fun inputTextEmployeePerfomer (text: String, list: List<UserCRMModel> ) {

        state = state.copy(

            employeePerformer = text,

            filteredListEmployee = list

        )

    }

    fun inputTextLocation (text: String, list: List<LocationResponseModel> ) {

        state = state.copy(

            location = text,

            filteredListLocations = list

        )

    }

    fun inputTextLegalEntity ( text: String, list: List<ContragentResponseModel> ) {

        state = state.copy(

            legalEntity = text,

            filteredListContragents = list

        )

    }


    fun inputTextEmployee (text: String, list: List<UserCRMModel> ) {

        state = state.copy(

            employee = text,

            filteredListEmployee = list

        )

    }

    fun inputTextAdditionalFields ( text: String, index: Int ) {

        val newList = state.textFieldsValues.toMutableList()

        newList[index] = text

        state = state.copy(

            textFieldsValues = newList,

        )

    }

    fun inputTextProject( text: String, list: List<ProjectResponseModel> ) {

        state = state.copy(

            project = text,

            filteredListProjects = list

            )

    }

    fun inputTextStatusText ( text: String ) {

        state = state.copy(

            statusText = text

        )

    }

    fun inputTextTask ( text: String ) {

        state = state.copy(

            task = text

        )

    }

    fun setScreen ( listSpecifications: List<SpecificResponseModel>,

                    listServices: List<ServiceResponseModel> ,

                    listEmployee: List<UserCRMModel> ,

                    listContragents: List<ContragentResponseModel> ,

                    listLocations: List<LocationResponseModel>,

                    listCargo: List<CargoResponseModel>,

                    listGroupEntity: List<GroupEntityResponseModel>,

                    listProjects: List<ProjectResponseModel>,

                    item: ApiResponseCRMModel? ) {

        if ( state.isSet ) {

            var service:ServiceResponseModel? = null

           listServices.forEach {

               if ( it.id == item?.service_id ) {


               service = it


               }

           }

            val newListValues = mutableListOf<String>()

            var newSelectedItems: ValDetailModel? = null

             if ( item?.value != null  ) {

                 item.value.forEachIndexed() { index, it ->

                 newListValues.add(item.value[index].`val`?.raw ?: "")

                     if (item.value[index].`val`?.raw  != null) {

                         newListValues.add(item.value[index].`val`?.raw ?: "")

                     } else if ( item.value[index].`val`?.detail != null &&

                         item.value[index].`val`?.detail!!.id != 0 ) {

                         newListValues.add(item.value[index].`val`?.detail?.id.toString() ?: "")

                         newSelectedItems = item.value[index].`val`?.detail
                     }

                 }

             }

            state = state.copy(

                filteredListEmployee = listEmployee,

                filteredListContragents = listContragents,

                filteredListLocations = listLocations,

                filteredListServices = listServices,

                filteredListSpecifications = listSpecifications,

                filteredListCargo = listCargo,

                filteredListGroupEntity = listGroupEntity,

                filteredListProjects = listProjects,

                selectedGroupEntity = if ( item?.groupentits != null ) GroupEntityResponseModel(

                    id = item.groupentits.id,
                    name = item.groupentits.name,
                    0,
                    "",
                    "",
                    ""

                ) else null,

                selectedLegalEntity = if ( item?.entity != null ) EntityContragentModel(

                    id = item.entity.id,
                    name = item.entity.name,
                    ui = ""

                ) else null,

                selectedLegalEntityPerformer = if ( item?.entity_our != null ) EntityContragentModel(

                    id = item.entity_our.id,
                    name = item.entity_our.name,
                    ui = ""

                ) else null,

                selectedProject = if ( item?.project_id != null )

                    listProjects.find { it.id == item.project_id } else null,

                selectedLocation = if ( item?.to_local_id != null )

                    listLocations.find { it.id == item.to_local_id } else null,


                selectedLocationPerformer = if ( item?.from_local_id != null )

                    listLocations.find { it.id == item.from_local_id } else null,

                selectedPaidFor =  if ( item?.status_pay != null && item.status_pay.isNotBlank() )

                    item.status_pay.toInt().let {

                    when (it) {
                        0 -> Pair("Не оплачено", 0)
                        1 -> Pair("Оплачено", 1)
                        else -> null
                    }
                } else null ,

                selectedVerified = if ( item?.verify_pay != null && item.verify_pay.isNotBlank() )

                    item.verify_pay.toInt().let {
                    when (it) {
                        0 -> Pair("Не подтвержден", 0)
                        1 -> Pair("Подтвержден", 1)
                        else -> null
                    }
                } else null ,

                task = item?.task?:"",

                statusText = item?.status?:"",

                selectedSpecific = if ( item?.specification_id != null )

                    listSpecifications.find { it.id == item.specification_id } else null,

                selectedStatus = Pair(

                    when (item?.statusid) {
                        0 -> "Завершена"
                        1 -> "Активна"
                        2 -> "В работе"
                        3 -> "Срочная"
                        4 -> "Не выполнена"
                        5 -> "Выполнена не до конца"
                        6 -> "Отложена"
                        else -> "" // Добавлено корректное завершение when с else
                    },
                    item?.statusid ?: 1
                ),

                selectedService = if ( service != null ) ServiceResponseModel(

                    id = service!!.id,
                    name = service!!.name,
                    text = service!!.text,
                    doc = service!!.doc,
                    ui = item?.ui,
                    system = null,
                    width = null,
                    height = null,
                    service_type_doc = null,
                    comp_project = service!!.comp_project,
                    view = null,
                    view_comp_project = null,
                    store = null,
                    delete_status = null,
                    company_id = null,
                    created_at = null,
                    updated_at = null,
                    need_check = null,
                    need_check_our = null,
                    limit_group_entity_id = null,
                    default_entity_id = null,
                    background = null,
                    image = null,
                    items = service!!.items

                ) else null,

                isSet = false,

                updatedSelectItem = newSelectedItems,

                textFieldsValues = newListValues

            )

            println("SELECTED PROJECT :  ${state.selectedProject}")

        }


    }

    fun totalPrice () {

            var priceTotal = 0.0

        if ( state.selectedSpecific != null ) {

            state.selectedSpecific!!.spec_item!!.forEach {

                priceTotal += it.price ?: 0.0

            }
        }

            state = state.copy(

                totalPrice = priceTotal

            )


    }

    fun colorTF () {

        state = state.copy(

            borderServiceColor = Color.Red,

            containerServiceColor = Color.Red

        )

    }

}