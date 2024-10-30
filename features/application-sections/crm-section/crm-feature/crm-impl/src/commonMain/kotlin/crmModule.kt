import com.project.network.crm_network.CRMClient
import datasource.CRMClientImpl
import domain.repository.CRMClientApi
import domain.usecases.GetIncomingCRMUseCase
import org.koin.dsl.module
import viewmodel.CRMViewModel

val crmModule = module {

    factory { CRMScreenImpl() as CRMScreenApi }

    factory { GetIncomingCRMUseCase(get()) }

    factory { CRMViewModel( get() ) }

    factory { CRMClient() }

    factory { CRMClientImpl( get() ) as CRMClientApi }

}