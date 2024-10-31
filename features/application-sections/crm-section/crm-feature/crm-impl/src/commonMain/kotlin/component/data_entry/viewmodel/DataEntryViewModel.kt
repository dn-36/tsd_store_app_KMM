package component.data_entry.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import model.EntityContragentModel
import model.LocationResponseModel
import model.ServiceResponseModel
import model.SpecificResponseModel
import model.UserCRMModel

class DataEntryViewModel (): ViewModel() {

    var state by mutableStateOf(DataEntryState())

    fun processIntents ( intent: DataEntryIntents ) {

        when ( intent ) {

            is DataEntryIntents.MenuEmployee -> menuEmployee()

            is DataEntryIntents.MenuGoodsAndServices -> menuGoodsAndServices()

            is DataEntryIntents.MenuLocations -> menuLocations()

            is DataEntryIntents.MenuLegalEntityPerformer -> menuLegalEntityPerformer()

            is DataEntryIntents.MenuServices -> menuServices()

            is DataEntryIntents.MenuSpecifications -> menuSpecifications()

            is DataEntryIntents.MenuStatus -> menuStatus()


            is DataEntryIntents.SelectEmployee -> selectEmployee( intent.item )

            is DataEntryIntents.SelectLocation -> selectLocation( intent.item )

            is DataEntryIntents.SelectLegalEntityPerformer -> selectLegalEntityPerformer( intent.item )

            is DataEntryIntents.SelectService -> selectService( intent.item )

            is DataEntryIntents.SelectSpecification -> selectSpecific( intent.item )

            is DataEntryIntents.SelectGoodsAndServices -> selectArrivalAndServices( intent.item )

            is DataEntryIntents.SelectStatus -> selectStatus( intent.item )


            is DataEntryIntents.DeleteSelectedEmployee -> deleteSelectedEmployee()

            is DataEntryIntents.DeleteSelectedLegalEntityPerformer -> {

                deleteSelectedLegalEntityPerformer()
            }

            is DataEntryIntents.DeleteSelectedLocation -> deleteSelectedLocation()

            is DataEntryIntents.DeleteSelectedSpecification -> deleteSelectedSpecific()

            is DataEntryIntents.DeleteSelectedStatus -> deleteSelectedStatus()

            is DataEntryIntents.DeleteSelectedService -> deleteSelectedService()

            is DataEntryIntents.DeleteSelectedGoodsAndServices -> deleteSelectedGoodsAndServices()


            is DataEntryIntents.InputTextLocation -> inputTextLocation( intent.text, intent.list )

            is DataEntryIntents.InputTextLegalEntityPerformer -> inputTextLegalEntityPerformer(

                intent.text, intent.list )

            is DataEntryIntents.InputTextService -> inputTextService( intent.text, intent.list )

            is DataEntryIntents.InputTextSpecification -> inputTextSpecific( intent.text,

                intent.list )

            is DataEntryIntents.InputTextStatus -> inputTextStatus( intent.text, intent.list )

            is DataEntryIntents.InputTextGoodsAndServices -> {

                inputTextGoodsAndServices( intent.text, intent.list )
            }

            is DataEntryIntents.InputTextEmployee -> inputTextEmployee( intent.text, intent.list )

            is DataEntryIntents.InputTextStatusText -> inputTextStatusText( intent.text )

            is DataEntryIntents.InputTextComment -> inputTextComment( intent.text )

            is DataEntryIntents.InputTextFIO -> inputTextFIO( intent.text )

            is DataEntryIntents.InputTextPhoneNumber -> inputTextPhoneNumber( intent.text )

            is DataEntryIntents.InputTextTask -> inputTextTask( intent.text )


            is DataEntryIntents.SetScreen -> { setScreen( intent.listSpecifications,

                intent.listServices, intent.listEmployee, intent.listLegalEntities,

                intent.listLocations)

            }


        }

    }

    fun menuServices () {

        state = state.copy(

            expendedService = !state.expendedService,
            expendedStatus = false,
            expendedEmployee = false,
            expendedLegalEntityPerformer = false,
            expendedSpecifications = false,
            expendedLocations = false,
            expendedGoodsAndServices = false

        )

    }

    fun menuLegalEntityPerformer () {

        state = state.copy(

            expendedLegalEntityPerformer = !state.expendedLegalEntityPerformer,
            expendedStatus = false,
            expendedEmployee = false,
            expendedSpecifications = false,
            expendedLocations = false,
            expendedGoodsAndServices = false,
            expendedService = false

        )

    }

    fun menuEmployee () {

        state = state.copy(

            expendedEmployee = !state.expendedEmployee,
            expendedStatus = false,
            expendedLegalEntityPerformer = false,
            expendedGoodsAndServices = false,
            expendedSpecifications = false,
            expendedLocations = false,
            expendedService = false

        )

    }

    fun menuLocations () {

        state = state.copy(

            expendedLocations = !state.expendedLocations,
            expendedSpecifications = false,
            expendedService = false,
            expendedGoodsAndServices = false,
            expendedStatus = false,
            expendedEmployee = false,
            expendedLegalEntityPerformer = false

        )

    }

    fun menuStatus () {

        state = state.copy(

            expendedStatus = !state.expendedStatus,
            expendedEmployee = false,
            expendedLegalEntityPerformer = false,
            expendedGoodsAndServices = false,
            expendedService = false,
            expendedSpecifications = false,
            expendedLocations = false


        )

    }

    fun menuGoodsAndServices () {

        state = state.copy(

            expendedGoodsAndServices = !state.expendedGoodsAndServices,
            expendedSpecifications = false,
            expendedLocations = false,
            expendedService = false,
            expendedLegalEntityPerformer = false,
            expendedEmployee = false,
            expendedStatus = false

        )

    }

    fun menuSpecifications () {

        state = state.copy(

            expendedSpecifications = !state.expendedSpecifications,
            expendedEmployee = false,
            expendedStatus = false,
            expendedLegalEntityPerformer = false,
            expendedService = false,
            expendedLocations = false,
            expendedGoodsAndServices = false

        )

    }

    fun selectService ( item: ServiceResponseModel ) {

    state = state.copy(

        selectedService = item,

        expendedService = false

    )

    }

    fun selectSpecific ( item: SpecificResponseModel ) {

        state = state.copy(

            selectedSpecific = item,

            expendedSpecifications = false

        )

    }

    fun selectLegalEntityPerformer ( item: EntityContragentModel ) {

        state = state.copy(

            selectedLegalEntityPerformer = item,

            expendedLegalEntityPerformer = false

        )

    }

    fun selectLocation ( item: LocationResponseModel ) {

        state = state.copy(

            selectedLocation = item,

            expendedLocations = false

        )

    }

    fun selectStatus ( item: String ) {

        state = state.copy(

            selectedStatus = item,

            expendedStatus = false

        )

    }

    fun selectArrivalAndServices ( item: SpecificResponseModel ) {

        state = state.copy(

            selectedGoodsAndServices = item,

            expendedGoodsAndServices = false

        )

    }

    fun selectEmployee ( item: UserCRMModel ) {

        state = state.copy(

            selectedEmployee = item,

            expendedEmployee = false

        )

    }


    fun deleteSelectedService ( ) {

        state = state.copy(

            selectedService = null

        )

    }

    fun deleteSelectedSpecific ( ) {

        state = state.copy(

            selectedSpecific = null

        )

    }

    fun deleteSelectedLegalEntityPerformer ( ) {

        state = state.copy(

            selectedLegalEntityPerformer = null

        )

    }

    fun deleteSelectedLocation ( ) {

        state = state.copy(

            selectedLocation = null

        )

    }

    fun deleteSelectedStatus (  ) {

        state = state.copy(

            selectedStatus = ""

        )

    }

    fun deleteSelectedGoodsAndServices ( ) {

        state = state.copy(

            selectedGoodsAndServices = null

        )

    }

    fun deleteSelectedEmployee (  ) {

        state = state.copy(

            selectedEmployee = null

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

    fun inputTextLocation ( text: String, list: List<LocationResponseModel> ) {

        state = state.copy(

            location = text,

            filteredListLocations = list

        )

    }

    fun inputTextLegalEntityPerformer ( text: String, list: List<EntityContragentModel> ) {

        state = state.copy(

            legalEntityPerformer = text,

            filteredListLegalEntities = list

        )

    }

    fun inputTextStatus ( text: String, list: List<String> ) {

        state = state.copy(

            status = text,

            filteredListStatus = list

        )

    }

    fun inputTextGoodsAndServices ( text: String, list: List<SpecificResponseModel> ) {

        state = state.copy(

            goodsAndServices = text,

            filteredListGoodsAndServices = list

        )

    }

    fun inputTextEmployee ( text: String, list: List<UserCRMModel> ) {

        state = state.copy(

            employee = text,

            filteredListEmployee = list

        )

    }

    fun inputTextStatusText ( text: String ) {

        state = state.copy(

            statusText = text

        )

    }

    fun inputTextComment ( text: String ) {

        state = state.copy(

            comment = text

        )

    }

    fun inputTextFIO ( text: String ) {

        state = state.copy(

            fio = text

        )

    }

    fun inputTextPhoneNumber ( text: String ) {

        state = state.copy(

            phoneNumber = text

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

                    listLegalEntities: List<EntityContragentModel> ,

                    listLocations: List<LocationResponseModel> ) {

        if ( state.isSet ) {

            state = state.copy(

                filteredListEmployee = listEmployee,

                filteredListLegalEntities = listLegalEntities,

                filteredListLocations = listLocations,

                filteredListServices = listServices,

                filteredListSpecifications = listSpecifications,

                filteredListStatus = listOf(
                    "Активна", "В работе", "Срочная", "Завершена", "Не выполнена",

                    "Выполнена не до конца", "Отложена"
                ),

                filteredListGoodsAndServices = listSpecifications,

                isSet = false

            )

        }


    }

}