import com.project.network.contragent_network.ContragentClient
import org.koin.dsl.module
import screen.datasource.ContragentsClientImpl
import screen.domain.repository.ContragentsClientApi
import screen.domain.usecases.CreateContragentUseCase
import screen.domain.usecases.DeleteContragentUseCase
import screen.domain.usecases.GetContragentsUseCase
import screen.domain.usecases.UpdateContragentUseCase
import screen.viewmodel.ContragentsViewModel

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