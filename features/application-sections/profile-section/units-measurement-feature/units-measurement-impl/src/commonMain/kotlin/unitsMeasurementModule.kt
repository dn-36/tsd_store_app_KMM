import com.project.network.units_measurement_network.UnitsMeasurementClient
import datasource.UnitsMeasurementClientImpl
import domain.repository.UnitsMeasurementClientApi
import domain.usecases.CreateUnitsMeasurementUseCase
import domain.usecases.DeleteUnitMeasurementUseCase
import domain.usecases.GetUnitsMeasurementUseCase
import domain.usecases.UpdateUnitsMeasurementUseCase
import org.koin.dsl.module
import viewmodel.UnitsMeasurementViewModel

val unitsMeasurementModule = module {

    factory { UnitsMeasurementScreenImpl() as UnitsMeasurementScreenApi }

    factory { UnitsMeasurementClientImpl( get(), get() ) as UnitsMeasurementClientApi }

    factory { GetUnitsMeasurementUseCase( get() ) }

    factory { CreateUnitsMeasurementUseCase( get() ) }

    factory { DeleteUnitMeasurementUseCase( get() ) }

    factory { UpdateUnitsMeasurementUseCase( get() ) }

    factory { UnitsMeasurementViewModel( get(), get(), get(), get() ) }

    factory { UnitsMeasurementClient() }

}