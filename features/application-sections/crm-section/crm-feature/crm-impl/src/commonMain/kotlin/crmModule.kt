import com.project.network.cargo_network.CargoClient
import com.project.network.contragent_network.ContragentClient
import com.project.network.crm_network.CRMClient
import com.project.network.locations_network.LocationsClient
import com.project.network.projects_network.ProjectsClient
import com.project.network.services_network.ServicesClient
import com.project.network.specifications_network.SpecificationsClient
import com.project.network.users_network.UsersClient
import datasource.CRMClientImpl
import domain.repository.CRMClientApi
import domain.usecases.CreateCRMUseCase
import domain.usecases.GetCargoUseCase
import domain.usecases.GetIncomingCRMUseCase
import domain.usecases.GetContragentsUseCase
import domain.usecases.GetOutgoingCRMUseCase
import domain.usecases.GetServicesUseCase
import domain.usecases.GetSpecificationsUseCase
import domain.usecases.GetEmployeeUseCase
import domain.usecases.GetLocationsUseCase
import domain.usecases.GetProjectsUseCase
import org.koin.dsl.module
import viewmodel.CRMViewModel

val crmModule = module {

    factory { CRMScreenImpl() as CRMScreenApi }

    factory { GetIncomingCRMUseCase(get()) }

    factory { GetProjectsUseCase(get()) }

    factory { GetOutgoingCRMUseCase(get()) }

    factory { GetSpecificationsUseCase(get()) }

    factory { GetCargoUseCase(get()) }

    factory { CreateCRMUseCase(get()) }

    factory { GetServicesUseCase(get()) }

    factory { GetContragentsUseCase(get()) }

    factory { GetEmployeeUseCase(get()) }

    factory { GetLocationsUseCase(get()) }

    factory { CRMViewModel( get(), get(), get(), get(), get(), get(), get(), get(), get(), get() ) }

    factory { CRMClient() }

    factory { LocationsClient() }

    factory { ProjectsClient() }

    factory { ServicesClient() }

    factory { SpecificationsClient() }

    factory { ContragentClient() }

    factory { CargoClient() }

    factory { UsersClient() }

    factory { CRMClientImpl( get(), get(), get(), get(), get(), get(), get(), get(), get() ) as CRMClientApi }

}