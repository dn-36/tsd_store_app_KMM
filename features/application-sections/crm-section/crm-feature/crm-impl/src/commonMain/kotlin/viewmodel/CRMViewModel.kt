package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.`menu-crm-api`.MenuCrmScreenApi
import com.project.network.Navigation
import domain.usecases.CreateCRMUseCase
import domain.usecases.GetCargoUseCase
import domain.usecases.GetIncomingCRMUseCase
import domain.usecases.GetContragentsUseCase
import domain.usecases.GetOutgoingCRMUseCase
import domain.usecases.GetServicesUseCase
import domain.usecases.GetSpecificationsUseCase
import domain.usecases.GetEmployeeUseCase
import domain.usecases.GetGroupEntityUseCase
import domain.usecases.GetLocationsUseCase
import domain.usecases.GetProductsUseCase
import domain.usecases.GetProjectsUseCase
import domain.usecases.UpdateCRMUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform

class CRMViewModel (

    val getIncomingCRMUseCase: GetIncomingCRMUseCase,

    val getOutgoingCRMUseCase: GetOutgoingCRMUseCase,

    val getServicesUseCase: GetServicesUseCase,

    val getSpecificationsUseCase: GetSpecificationsUseCase,

    val getUsersUseCase: GetEmployeeUseCase,

    val getContragentsUseCase: GetContragentsUseCase,

    val getLocationsUseCase: GetLocationsUseCase,

    val getProjectsUseCase: GetProjectsUseCase,

    val createCRMUseCase: CreateCRMUseCase,

    val getCargoUseCase: GetCargoUseCase,

    val getGroupEntityUseCase: GetGroupEntityUseCase,

    val updateCRMUseCase: UpdateCRMUseCase,

    val getProductsUseCase: GetProductsUseCase

): NetworkViewModel() {

    var state by mutableStateOf( CRMState() )

    fun processIntents ( intent: CRMIntents ){

        when ( intent ) {

            is CRMIntents.SetScreen -> {

                if ( state.isSet ) {

                    intent.coroutineScope.launch(Dispatchers.IO) {

                        state = state.copy(

                            listIncomingCRM = getIncomingCRMUseCase.execute(),

                            listFilteredIncomingCRM = getIncomingCRMUseCase.execute(),

                            listOutgoingCRM = getOutgoingCRMUseCase.execute(),

                            listFilteredOutgoingCRM = getOutgoingCRMUseCase.execute(),

                            isSet = false

                        )

                        setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                    }
                }

            }

            is CRMIntents.Back ->  back()

            is CRMIntents.SelectTypeCRM ->  selectTypeCRM( intent.index )

            is CRMIntents.BackToCRMComponent ->  backToCRMComponent()

            is CRMIntents.OpenCreateDataEntryComponent -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

             intent.coroutineScope.launch ( Dispatchers.IO ) {

                 state = state.copy(

                     listServices = getServicesUseCase.execute(),

                     listSpecifications = getSpecificationsUseCase.execute(),

                     listEmployee = getUsersUseCase.execute(),

                     listContragents = getContragentsUseCase.execute(),

                     listLocations = getLocationsUseCase.execute(),

                     listCargo = getCargoUseCase.execute(),

                     listGroupEntity = getGroupEntityUseCase.execute(),

                     listProducts = getProductsUseCase.execute(),

                     listProjects = getProjectsUseCase.execute(),

                     isVisibilityDataEntryComponent = 1f

                 )

                 setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

             }

            }

            is CRMIntents.OpenUpdateDataEntryComponent -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                println("ITEM ${intent.item}")

                intent.coroutineScope.launch ( Dispatchers.IO ) {

                    state = state.copy(

                        listServices = getServicesUseCase.execute(),

                        listSpecifications = getSpecificationsUseCase.execute(),

                        listEmployee = getUsersUseCase.execute(),

                        listContragents = getContragentsUseCase.execute(),

                        listLocations = getLocationsUseCase.execute(),

                        listCargo = getCargoUseCase.execute(),

                        listGroupEntity = getGroupEntityUseCase.execute(),

                        listProducts = getProductsUseCase.execute(),

                        listProjects = getProjectsUseCase.execute(),

                        updateItem = intent.item,

                        isVisibilityDataEntryComponent = 1f

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is CRMIntents.CreateCRM -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch ( Dispatchers.IO ) {

                createCRMUseCase.execute ( serviceId = intent.serviceId,

                    statusPay = intent.statusPay, verifyPay = intent.verifyPay,

                    task = intent.task, to_local_id = intent.to_local_id,

                    group_entity_id = intent.group_entity_id, from_local_id = intent.from_local_id,

                    status = intent.status, price = intent.price, arendaId = intent.arendaId,

                    specificationId = intent.specificationId,projectId = intent.projectId,

                    entityId = intent.entityId, ourEntityId = intent.ourEntityId,

                    text = intent.text, statusId = intent.statusId, items = intent.items )

                state = state.copy(

                    isVisibilityDataEntryComponent = 0f,

                    listOutgoingCRM = getOutgoingCRMUseCase.execute(),

                    listFilteredOutgoingCRM = getOutgoingCRMUseCase.execute()

                )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is CRMIntents.UpdateCRM -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch ( Dispatchers.IO ) {

                    updateCRMUseCase.execute ( ui = intent.ui, serviceId = intent.serviceId,

                        statusPay = intent.statusPay, verifyPay = intent.verifyPay,

                        task = intent.task, to_local_id = intent.to_local_id,

                        group_entity_id = intent.group_entity_id, from_local_id = intent.from_local_id,

                        status = intent.status, price = intent.price, arendaId = intent.arendaId,

                        specificationId = intent.specificationId,projectId = intent.projectId,

                        entityId = intent.entityId, ourEntityId = intent.ourEntityId,

                        text = intent.text, statusId = intent.statusId, items = intent.items )

                    state = state.copy(

                        isVisibilityDataEntryComponent = 0f,

                        updateItem = null,

                        listOutgoingCRM = getOutgoingCRMUseCase.execute(),

                        listFilteredOutgoingCRM = getOutgoingCRMUseCase.execute()

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is CRMIntents.InputTextSearchComponent -> inputTextSearchComponent(intent.text)

            is CRMIntents.LongPressItem -> longPressItem(intent.index)

            is CRMIntents.OnePressItem -> onePressItem()

        }

    }

    fun back () {

         println(" CHECK CRM: ${state.listIncomingCRM} ")

         println(" CHECK OUTGOING CRM: ${state.listOutgoingCRM} ")

         println(" CHECK SPECIFICATIONS: ${state.listSpecifications} ")

         println(" CHECK SERVICES: ${state.listServices} ")

         println(" CHECK PRODUCTS: ${state.listProducts} ")

         println(" CHECK PROJECTS: ${state.listProjects} ")

         println(" CHECK CONTRGENTS: ${state.listContragents} ")

         println(" CHECK EMPLOYEE: ${state.listEmployee} ")

         println(" CHECK CARGO: ${state.listCargo} ")

         println(" CHECK GROUP ENTITY: ${state.listGroupEntity} ")


        val menuScreen: MenuCrmScreenApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push( menuScreen.MenuCrm() )

    }

    fun selectTypeCRM ( index: Int ) {

        when ( index ) {

            1 -> { state = state.copy(

                isIncoming = true,

                isOutgoing = false

            ) }

            2 -> { state = state.copy(

                isIncoming = false,

                isOutgoing = true

            ) }

        }

    }

    fun backToCRMComponent () {

        state = state.copy(

            isVisibilityDataEntryComponent = 0f,

            updateItem = null

        )

    }

    fun inputTextSearchComponent( text: String ) {

        if ( state.isIncoming ) {

            val newList = state.listIncomingCRM.filter {

                val companyName = it.entity_our?.name.orEmpty()

                companyName.contains(text, ignoreCase = true)

            }

            state = state.copy(

                listFilteredIncomingCRM = newList

            )
        }

        else if ( state.isOutgoing ) {

            val newList = state.listOutgoingCRM.filter {

                val companyName = it.entity_our?.name.orEmpty()

                companyName.contains(text, ignoreCase = true)

            }

            state = state.copy(

                listFilteredOutgoingCRM = newList

            )
        }

    }

    fun longPressItem ( index: Int ) {

            val newList = MutableList(state.listOutgoingCRM.size) { 0F }

            newList[index] = 1f

            state = state.copy(

                listAlphaTools = newList

            )

    }

    fun onePressItem () {

            val newList = MutableList(state.listOutgoingCRM.size) { 0F }

            state = state.copy(

                listAlphaTools = newList

            )

    }

}