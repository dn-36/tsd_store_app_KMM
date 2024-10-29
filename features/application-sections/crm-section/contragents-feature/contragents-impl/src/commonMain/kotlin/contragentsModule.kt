import com.project.network.contragent_network.ContragentClient
import org.koin.dsl.module
import screens.datasource.ContragentsClientImpl
import screens.domain.repository.ContragentsClientApi
import screens.domain.usecases.CreateContragentUseCase
import screens.domain.usecases.DeleteContragentUseCase
import screens.domain.usecases.GetContragentsUseCase
import screens.domain.usecases.UpdateContragentUseCase
import screens.viewmodel.ContragentsViewModel

val contragentsModule = module {

    factory { ContragentsScreensImpl() as ContragentsScreensApi }

    factory { GetContragentsUseCase( get() ) }

    factory { DeleteContragentUseCase( get() ) }

    factory { CreateContragentUseCase( get() ) }

    factory { UpdateContragentUseCase( get() ) }

    factory { ContragentClient()  }

    factory { ContragentsClientImpl( get() ) as ContragentsClientApi }

    factory { ContragentsViewModel( get(), get(), get(), get() )  }
}