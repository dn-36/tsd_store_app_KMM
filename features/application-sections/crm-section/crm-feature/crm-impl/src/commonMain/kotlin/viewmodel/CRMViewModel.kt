package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.`menu-crm-api`.MenuCrmScreenApi
import com.project.network.Navigation
import domain.usecases.CreateCRMUseCase
import domain.usecases.GetIncomingCRMUseCase
import domain.usecases.GetLegalEntitiesUseCase
import domain.usecases.GetOutgoingCRMUseCase
import domain.usecases.GetServicesUseCase
import domain.usecases.GetSpecificationsUseCase
import domain.usecases.GetEmployeeUseCase
import domain.usecases.GetLocationsUseCase
import domain.usecases.GetProjectsUseCase
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

    val getLegalEntitiesUseCase: GetLegalEntitiesUseCase,

    val getLocationsUseCase: GetLocationsUseCase,

    val getProjectsUseCase: GetProjectsUseCase,

    val createCRMUseCase: CreateCRMUseCase

): NetworkViewModel() {

    var state by mutableStateOf( CRMState() )

    fun processIntents ( intent: CRMIntents ){

        when ( intent ) {

            is CRMIntents.SetScreen -> {

                if ( state.isSet ) {

                    intent.coroutineScope.launch(Dispatchers.IO) {

                        state = state.copy(

                            listIncomingCRM = getIncomingCRMUseCase.execute(),

                            listOutgoingCRM = getOutgoingCRMUseCase.execute().toMutableList(),

                            listProjects = getProjectsUseCase.execute(),

                            isSet = false

                        )

                        setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                    }
                }

            }

            is CRMIntents.Back ->  back()

            is CRMIntents.SelectTypeCRM ->  selectTypeCRM( intent.index )

            is CRMIntents.BackToCRMComponent ->  backToCRMComponent()

            is CRMIntents.OpenDataEntryComponent -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

             intent.coroutineScope.launch ( Dispatchers.IO ) {

                 state = state.copy(

                     listServices = getServicesUseCase.execute(),

                     listSpecifications = getSpecificationsUseCase.execute(),

                     listEmployee = getUsersUseCase.execute(),

                     listLegalEntities = getLegalEntitiesUseCase.execute(),

                     listLocations = getLocationsUseCase.execute(),

                     isVisibilityDataEntryComponent = 1f

                 )

                 setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

             }

            }

            is CRMIntents.CreateCRM -> {

            intent.coroutineScope.launch ( Dispatchers.IO ) {

                createCRMUseCase.execute ( serviceId = intent.serviceId,

                    statusPay = intent.statusPay, verifyPay = intent.verifyPay,

                    task = intent.task, price = intent.price, arendaId = intent.arendaId,

                    specificationId = intent.specificationId,projectId = intent.projectId,

                    entityId = intent.entityId,

                    ourEntityId = intent.ourEntityId,

                    text = intent.text, statusId = intent.statusId, items = intent.items )

                state = state.copy(

                    isVisibilityDataEntryComponent = 0f,

                    listOutgoingCRM = getOutgoingCRMUseCase.execute().toMutableList()

                )

            }

            }

        }

    }

    fun back () {

         println(" CHECK CRM: ${state.listIncomingCRM} ")

         println(" CHECK OUTGOING CRM: ${state.listOutgoingCRM} ")

         println(" CHECK SPECIFICATIONS: ${state.listSpecifications} ")

         println(" CHECK SERVICES: ${state.listServices} ")

         println(" CHECK PROJECTS: ${state.listProjects} ")

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

            isVisibilityDataEntryComponent = 0f

        )

    }

}