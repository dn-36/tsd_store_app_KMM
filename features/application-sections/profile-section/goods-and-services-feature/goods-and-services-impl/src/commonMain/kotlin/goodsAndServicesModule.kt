import com.project.network.category_network.CategoryClient
import com.project.network.characterisrics_products_network.CharacteristicsClient
import com.project.network.common.ConstData
import com.project.network.system_category_network.SystemCategoryClient
import com.project.network.units_measurement_network.UnitsMeasurementClient
import datasource.GoodsAndServicesClientImpl
import domain.repository.GoodsAndServicesClientApi
import domain.usecases.CreateGoodOrServiceUseCase
import domain.usecases.DeleteGoodOrServiceUseCase
import domain.usecases.GetCategoryUseCase
import domain.usecases.GetCharacteristicsUseCase
import domain.usecases.GetGoodsAndServicesUseCase
import domain.usecases.GetSystemCategoryUseCase
import domain.usecases.GetUnitsGoodsAndServicesUseCase
import domain.usecases.UpdateGoodOrServiceUseCase
import org.koin.dsl.module
import product_network.ProductApiClient
import viewmodel.GoodsAndServicesViewModel

val goodsAndServicesModule = module {

    factory { GoodsAndServicesScreenImpl() as GoodsAndServicesScreenApi }

    factory { GoodsAndServicesClientImpl(get(), get(), get(), get(),

        get(), get()) as GoodsAndServicesClientApi }

    factory { GetGoodsAndServicesUseCase(get()) }

    factory { GetSystemCategoryUseCase(get()) }

    factory { GetCategoryUseCase(get()) }

    factory { GetUnitsGoodsAndServicesUseCase(get()) }

    factory { CreateGoodOrServiceUseCase(get()) }

    factory { DeleteGoodOrServiceUseCase(get()) }

    factory { UpdateGoodOrServiceUseCase(get()) }

    factory { GetCharacteristicsUseCase(get()) }

    factory { GoodsAndServicesViewModel( get(), get(), get(), get(), get(), get(), get(), get()) }

    factory { ProductApiClient(ConstData.TOKEN) }

    factory { SystemCategoryClient() }

    factory { CategoryClient() }

    factory { UnitsMeasurementClient() }

    factory { CharacteristicsClient() }

}