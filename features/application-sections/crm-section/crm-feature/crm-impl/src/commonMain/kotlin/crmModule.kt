import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.crm_network.CRMClient
import datasource.CRMClientImpl
import domain.repository.CRMClientApi
import domain.usecases.GetIncomingCRMUseCase
import domain.usecases.GetOutgoingCRMUseCase
import org.koin.dsl.module
import viewmodel.CRMViewModel

val crmModule = module {

    factory { CRMScreenImpl() as CRMScreenApi }

    factory { GetIncomingCRMUseCase(get()) }

    factory { GetOutgoingCRMUseCase(get()) }

    factory { CRMViewModel( get(), get() ) }

    factory { CRMClient() }

   // factory { SharedPrefsApi() }

    factory { CRMClientImpl( get(), get() ) as CRMClientApi }

}